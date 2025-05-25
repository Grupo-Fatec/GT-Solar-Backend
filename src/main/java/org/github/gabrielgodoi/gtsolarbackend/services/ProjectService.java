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

import java.time.LocalDateTime;
import java.util.List;
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
        return projectList.stream().map(project -> {
            ProjectDto dto = this.projectMapper.entityToDto(project);
            if (project.getBudget() != null) {
                dto.setBudgetDto(
                        this.budgetMapper.entityToDto(project.getBudget())
                );
            }
            return dto;
        }).collect(Collectors.toList());
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

        Project retrieved = this.projectRepository.findByName(projectDto.getName());
        if (retrieved != null) {
            throw new AlreadyExistsException("Project already exists");
        }

        Project project = this.projectMapper.dtoToEntity(projectDto);
        project.setCreatedBy(admin);
        project.setClient(client);

        Project savedProject = this.projectRepository.save(project);
        Budget budget = this.budgetMapper.dtoToEntity(projectDto.getBudgetDto());

        Double approvedValue = 0.0;
        for (Details detail : projectDto.getBudgetDto().getDetails()) {
            approvedValue += detail.getPrice();
        }
        budget.setApprovedValue(approvedValue);
        budget.setDate(LocalDateTime.now());
        budget.setProject(savedProject);
        Budget savedBudget = this.budgetRepository.save(budget);
        savedProject.setBudget(savedBudget);
        this.projectRepository.save(savedProject);
        ProjectDto dto = this.projectMapper.entityToDto(savedProject);
        dto.setBudgetDto(this.budgetMapper.entityToDto(savedBudget));
        return dto;
    }


    public ProjectDto update(String projectId, UpdateProjectDto updateDto) {
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Project: " + projectId + " not found")
        );
        if (updateDto.getName() != null && !updateDto.getName().isBlank()) {
            String newName = updateDto.getName().trim();
            if (!newName.equalsIgnoreCase(project.getName())) {
                Project existing = this.projectRepository.findByName(newName);
                if (existing != null && !existing.getId().equals(projectId)) {
                    throw new AlreadyExistsException("Project: " + newName + " already in use");
                }
                project.setName(newName);
            }
        }
        if (updateDto.getStatus() != null) {
            project.setStatus(updateDto.getStatus());
        }
        if (updateDto.getObservations() != null && !updateDto.getObservations().isEmpty()) {
            for (String obs : updateDto.getObservations()) {
                if (obs != null && !obs.trim().isBlank()) {
                    project.getObservations().add(obs.trim());
                }
            }
        }
        project.setUpdated_at(LocalDateTime.now());
        Project updatedProject = this.projectRepository.save(project);
        return this.projectMapper.entityToDto(updatedProject);
    }

    public ProjectDto addStep(String projectId, Step step) {
        Project project = this.projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("User: " + projectId + " doesn't exists in our database")
        );
        project.getSteps().add(step);
        Project savedProject = this.projectRepository.save(project);
        return this.projectMapper.entityToDto(savedProject);
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

}
