package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.details.Details;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.InsertProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.ProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.UpdateProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.Budget;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.errors.AlreadyExistsException;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.BudgetRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ClientRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ProjectRepository;
import org.github.gabrielgodoi.gtsolarbackend.services.mappers.BudgetMapper;
import org.github.gabrielgodoi.gtsolarbackend.services.mappers.ProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;
    private final ProjectMapper projectMapper;

    public List<ProjectDto> findAll() {
        List<Project> projectList = this.projectRepository.findAll();
        return projectList.stream().map(this.projectMapper::entityToDto).collect(Collectors.toList());
    }

    public ProjectDto findOne(String id) {
        Project project = this.projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Project: " + id + " not found")
        );
        return this.projectMapper.entityToDto(project);
    }

    public List<ProjectDto> findFromClient(String clientId) {
        this.clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("User: " + clientId + " doesn't exists in our database")
        );
        List<Project> projectList = this.projectRepository.findAllByClientId(clientId).orElseThrow(
                () -> new EntityNotFoundException("User: " + clientId + " still don't have any project related to him!")
        );
        return projectList.stream().map(this.projectMapper::entityToDto).collect(Collectors.toList());
    }

    public ProjectDto create(String adminId, InsertProjectDto projectDto) {
        Admin admin = this.adminRepository.findById(adminId).orElseThrow(
                () -> new EntityNotFoundException("Admin: " + adminId + " not found")
        );
        Client client = this.clientRepository.findById(projectDto.getClientId()).orElseThrow(
                () -> new EntityNotFoundException("client: " + projectDto.getClientId() + " not found")
        );
        Project retrivied = this.projectRepository.findByName(projectDto.getName());

        if (retrivied != null) {
            throw new AlreadyExistsException("Projeto Já existe");
        }
        Project project = this.projectMapper.dtoToEntity(projectDto);
        project.setCreatedBy(admin);
        project.setClient(client);
        Project entity = this.projectRepository.save(project);
        return this.projectMapper.entityToDto(entity);
    }

    public ProjectDto update(String projectId, UpdateProjectDto updateDto) {
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Project: " + projectId + " not found")
        );
        if (updateDto.getName() != null && !updateDto.getName().isBlank()) {
            project.setName(updateDto.getName());
        }
        if (updateDto.getStatus() != null) {
            project.setStatus(updateDto.getStatus());
        }
        if (updateDto.getObservations() != null && !updateDto.getObservations().isEmpty()) {
            project.getObservations().addAll(updateDto.getObservations());
        }
        project.setUpdated_at(LocalDateTime.now());

        Project updatedProject = this.projectRepository.save(project);
        return this.projectMapper.entityToDto(updatedProject);
    }


    public String addDocument(String projectId, List<MultipartFile> files) {
        // chamar o serviço de upload de arquivos
        // fazer upload dos arquivos adicionados ao projeto em questão
        // enviar para o banco de dados atualizando aquele determinado projeto
        // enviar uma mensagem de retorno sobre os documentos adicionados
        return "Adicionado com sucesso";
    }

    public ProjectDto addStep(String projectId, Step step) {
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("User: " + projectId + " doesn't exists in our database")
        );
        project.getSteps().add(step);
        Project savedProject = this.projectRepository.save(project);
        return this.projectMapper.entityToDto(savedProject);
    }

    /*
    public BudgetDto addBudget(String projectId, InsertBudgetDto budgetDto) {
        Budget entity = new Budget();
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Project: " + projectId + " not found")
        );

        AtomicReference<Double> finalPrice = new AtomicReference<>(0.00);
        budgetDto.getDetails().forEach((d) -> {
            finalPrice.updateAndGet(v -> v + d.getPrice());
            entity.getDetails().add(d);
            d.setCreated_at(LocalDateTime.now());
            d.setUpdated_at(LocalDateTime.now());
        });
        entity.setDate(LocalDateTime.now());
        entity.setProject(project);
        entity.setApprovedValue(finalPrice.get());
        project.setApprovedValue(finalPrice.get());
        // devemos transformar esses orçamentos desse projeto em um pdf
        // devemos enviar esse pdf para o cliente
        // o projeto pode iniciar ?
        // o budget foi aprovado ??
        this.budgetRepository.save(entity);
        this.projectRepository.save(project);
        return new BudgetDto(entity);
    }
*/

    public void deleteOne(String projectId) {
        this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Project: " + projectId + " not found")
        );
        this.projectRepository.deleteById(projectId);
    }

    public void deleteMany(List<String> projectIds) {
        projectIds.forEach(id -> this.projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Project: " + id + " not found")
        ));

        this.projectRepository.deleteAllById(projectIds);
    }
}


    public void dtoToEntity(InsertProjectDto dto, Project entity) {
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        dto.getSteps().forEach(s -> entity.getSteps().add(s));
        entity.setCreated_at(LocalDateTime.now());
        entity.setUpdated_at(LocalDateTime.now());
    }

}
