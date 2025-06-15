package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.equipments.EquipmentsDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Equipment;
import org.github.gabrielgodoi.gtsolarbackend.entities.admins.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Engineer;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Installer;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.*;
import org.github.gabrielgodoi.gtsolarbackend.services.mappers.ProjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;
    private final ProjectMapper mapper;

    public List<ProjectDto> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public ProjectDto findOne(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project with id: " + id + " not found"));

        return mapper.toDto(project);
    }

    public List<ProjectDto> findFromClient(String clientId) {
        List<Project> projects = projectRepository.findAllByClientId(clientId).orElseThrow(
                () -> new EntityNotFoundException("No projects found for client with ID: " + clientId)
        );
        return projects.stream()
                .map(mapper::toDto)
                .toList();
    }


    // precisamos do id de um cliente,
    // precisamos do id do fornecedor de equipamentos -> ele estará na lista de equipamentos
    // consumo de energia da residência em que o projeto se iniciará
    // precisamos de um engenheiro -> vamos buscar do repo de persons(buscando um engenheiro por id)
    // precisamos de um instalador -> vamos buscar do repositório de person(buscando um instalador por id)
    // lista de equipamentos que irão ser usados no projeto -> vamos buscar do repositório de equipamentos
    public ProjectDto create(String adminEmail, InsertProjectDto projectDto) {
        // verificar se cada documento que precisamos existe
        Admin admin = this.adminRepository.findUserByEmail(adminEmail).orElseThrow(
                () -> new EntityNotFoundException("Admin com email '" + adminEmail + "' não foi encontrado")
        );

        Client client = this.clientRepository.findById(projectDto.clientId()).orElseThrow(
                () -> new EntityNotFoundException("Cliente com ID '" + projectDto.clientId() + "' não foi encontrado")
        );

        Engineer engineer = this.personRepository.findEngineerById(projectDto.engineerId()).orElseThrow(
                () -> new EntityNotFoundException("Engenheiro com ID '" + projectDto.engineerId() + "' não foi encontrado")
        );

        Installer installer = this.personRepository.findInstallerById(projectDto.installerId()).orElseThrow(
                () -> new EntityNotFoundException("Instalador com ID '" + projectDto.installerId() + "' não foi encontrado")
        );
        Double totalCost = this.employeesCost(projectDto.energyConsumption(), engineer.getValuePerKwh(), installer.getPricePerKwp()) + this.equipmentsCoast(projectDto.equipments());
        Project project = this.mapper.InsertToEntity(projectDto);
        project.setApprovedValue(totalCost);
        project.setEngineer(engineer);
        project.setInstaller(installer);
        project.setCreatedBy(admin);
        project.setClient(client);
        project.setStatus(StatusEnum.PLANNING);
        return this.mapper.toDto(this.projectRepository.save(project));
    }

    // método para aprovar um projeto(atualizando o campo aprrovedByClient)
    // devemos ter um método para o cliente, o engenheiro aprovarem o projeto
    // precisamos de uma ideia para que o instalador aceite(ou não) um projeto
    public void clientApproveProject(String projectId, String engineerId) {
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("projeto com ID '" + projectId + "' não foi encontrado")
        );
        this.personRepository.findEngineerById(engineerId).orElseThrow(
                () -> new EntityNotFoundException("engineer com ID '" + projectId + "' não foi encontrado")
        );

        project.setApprovedByEngineer(true);

        this.projectRepository.save(project);
    }

    public void engineerApproveProject(String projectId, String clientId) {
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("projeto com ID '" + projectId + "' não foi encontrado")
        );
        this.clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("client com ID '" + projectId + "' não foi encontrado")
        );

        project.setApprovedByClient(true);
        this.projectRepository.save(project);
    }

    public void installerAcceptProject(String projectId, String installerId) {
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("projeto com ID '" + projectId + "' não foi encontrado")
        );
        this.personRepository.findInstallerById(installerId).orElseThrow(
                () -> new EntityNotFoundException("installer com ID '" + projectId + "' não foi encontrado")
        );

        this.projectRepository.save(project);
    }

    public ProjectDto update(String projectId, InsertProjectDto projectDto) {
        Project retriviedData = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id: " + projectId + " not found"));

        retriviedData.setEngineer(projectDto.engineerId() != null
                ? this.personRepository.findEngineerById(projectDto.engineerId())
                .orElseThrow(() -> new EntityNotFoundException("Engineer com ID '" + projectDto.engineerId() + "' não foi encontrado"))
                : retriviedData.getEngineer()
        );
        retriviedData.setInstaller(projectDto.installerId() != null
                ? this.personRepository.findInstallerById(projectDto.installerId())
                .orElseThrow(() -> new EntityNotFoundException("Installer com ID '" + projectDto.installerId() + "' não foi encontrado"))
                : retriviedData.getInstaller()
        );
        retriviedData.setName(projectDto.name() != null ? projectDto.name() : retriviedData.getName());
        retriviedData.setEnergyConsumption(projectDto.energyConsumption() != null ? projectDto.energyConsumption() : retriviedData.getEnergyConsumption());
        if (projectDto.energyConsumption() != null) {
            Double costEmployees = this.employeesCost(
                    projectDto.energyConsumption(),
                    retriviedData.getEngineer().getValuePerKwh(),
                    retriviedData.getInstaller().getPricePerKwp()
            );
            Double costEquipments = this.equipmentsCoastUpdate(retriviedData.getEquipments());
            retriviedData.setApprovedValue(costEmployees + costEquipments);
        }
        Project updatedProject = this.projectRepository.save(retriviedData);
        return this.mapper.toDto(updatedProject); // Assumindo que você tem um mapper para converter Project -> ProjectDto
    }


    // após o projeto ser aprovado vamos adicionar passos a esse projeto
    // para que ele inicialize e dê continuidade
    // podemos adicionar passos para manutenção
    //public ProjectDto addStep(String projectId, Step step) {

    //}

    public void deleteOne(String projectId) {
        projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with ID: " + projectId + " not found"));

        projectRepository.deleteById(projectId);
    }

    public void deleteMany(List<String> projectIds) {
        projectIds.forEach(id -> {
            projectRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Project with ID: " + id + " not found"));
        });

        projectRepository.deleteAllById(projectIds);
    }

    // método para calcular a margem de lucro com base no custo total, um método separado vai desacoplar lógiva
    public Double employeesCost(Double kwp, Double engineerPerKwp, Double installerPerKwp) {
        return (kwp * engineerPerKwp) + (kwp * installerPerKwp);
    }

    public Double equipmentsCoast(List<EquipmentsDto> equipments) {
        Double value = 0.0;
        for (EquipmentsDto equipment : equipments) {
            value += equipment.price();
        }
        return value;
    }

    public Double equipmentsCoastUpdate(List<Equipment> equipments) {
        Double value = 0.0;
        for (Equipment equipment : equipments) {
            value += equipment.getPrice();
        }
        return value;
    }
}
