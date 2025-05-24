package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.BudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.InsertBudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Budget;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.BudgetRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ProjectRepository;
import org.github.gabrielgodoi.gtsolarbackend.services.mappers.BudgetMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final ProjectRepository projectRepository;
    private final BudgetMapper budgetMapper;

    public List<BudgetDto> findAll() {
        return budgetRepository.findAll()
                .stream()
                .map(budgetMapper::entityToDto)
                .toList();
    }

    public List<BudgetDto> findBudgetsByProject(String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));

        return budgetRepository.findByProject(project)
                .stream()
                .map(budgetMapper::entityToDto)
                .toList();
    }

    public BudgetDto create(String projectId, InsertBudgetDto insertBudgetDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));

        Budget budget = budgetMapper.dtoToEntity(insertBudgetDto);
        budget.setProject(project);
        budget.setDate(LocalDateTime.now());

        if (budget.getApprovedValue() == null) {
            budget.setApprovedValue(0.0);
        }

        Budget savedBudget = budgetRepository.save(budget);
        return budgetMapper.entityToDto(savedBudget);
    }

    public BudgetDto findOne(String budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new EntityNotFoundException("Budget not found with id: " + budgetId));
        return budgetMapper.entityToDto(budget);
    }

    public void deleteOne(String budgetId) {
        if (!budgetRepository.existsById(budgetId)) {
            throw new EntityNotFoundException("Budget not found with id: " + budgetId);
        }
        budgetRepository.deleteById(budgetId);
    }

    public void deleteByProject(String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));
        List<Budget> budgets = budgetRepository.findByProject(project);
        budgetRepository.deleteAll(budgets);
    }
}
