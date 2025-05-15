package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.BudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.InsertBudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.InsertProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.ProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.UpdateProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.Budget;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.BudgetRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ClientRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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

    public List<ProjectDto> findAll() {
        List<Project> projectList = this.projectRepository.findAll();
        return projectList.stream().map(ProjectDto::new).collect(Collectors.toList());
    }

    public ProjectDto findOne(String id) {
        Project project = this.projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Project: " + id + " not found")
        );
        return new ProjectDto(project);
    }

    public List<ProjectDto> findFromClient(String clientId) {
        this.clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("User: " + clientId + " doesn't exists in our database")
        );
        List<Project> projectList = this.projectRepository.findByClientId(clientId).orElseThrow(
                () -> new EntityNotFoundException("User: " + clientId + " still don't have any project related to him!")
        );
        return projectList.stream().map(ProjectDto::new).collect(Collectors.toList());
    }

    public ProjectDto create(InsertProjectDto projectDto) {
        try {
            Admin admin = this.adminRepository.findById(projectDto.getCreatedBy()).orElseThrow(
                    () -> new EntityNotFoundException("Admin: " + projectDto.getCreatedBy() + " not found")
            );
            String adminId = admin.getId();
            this.adminRepository.findById(adminId).orElseThrow(
                    () -> new EntityNotFoundException("Admin: " + adminId + " not found")
            );
            Project project = new Project();
            this.dtoToEntity(projectDto, project);
            project.setCreatedBy(admin);
            Project entity = this.projectRepository.save(project);
            return new ProjectDto(entity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
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
        return new ProjectDto(updatedProject);
    }


    public String addDocument(String projectId, List<MultipartFile> files) {
        // chamar o serviço de upload de arquivos
        // fazer upload dos arquivos adicionados ao projeto em questão
        // enviar para o banco de dados atualizando aquele determinado projeto
        // enviar uma mensagem de retorno sobre os documentos adicionados
        return "Adicionado com sucesso";
    }

    public ProjectDto addStep(String projectId, Step step){
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("User: " + projectId + " doesn't exists in our database")
        );
        project.getSteps().add(step);
        Project savedProject = this.projectRepository.save(project);
        return new ProjectDto(savedProject);
    }

    public BudgetDto addBudget(String projectId, InsertBudgetDto budgetDto) {
        Budget entity = new Budget();
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Project: " + projectId + " not found")
        );
        entity.setValue(entity.getValue());
        entity.setStatus(entity.getStatus());
        entity.setDate(LocalDateTime.now());
        budgetDto.getDetails().forEach(b -> entity.getDetails().add(b));
        entity.setProject(project);

        return new BudgetDto(entity);
    }

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

    public void updateData(Project entity, InsertProjectDto insertProjectDto) {
        entity.setUpdated_at(LocalDateTime.now());
    }

    public void dtoToEntity(InsertProjectDto dto, Project entity) {
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        dto.getSteps().forEach(s -> entity.getSteps().add(s));
        entity.setCreated_at(LocalDateTime.now());
        entity.setUpdated_at(LocalDateTime.now());
        AtomicReference<Double> value = new AtomicReference<>(0.0);
        dto.getBudgetList().forEach(budget -> value.updateAndGet(v -> v + budget.getValue()));
        entity.setApprovedValue(value.get());
    }
}
