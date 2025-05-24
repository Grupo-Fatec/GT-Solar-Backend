package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.BudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.InsertBudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.InsertProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.ProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.UpdateProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
import org.github.gabrielgodoi.gtsolarbackend.services.BudgetService;
import org.github.gabrielgodoi.gtsolarbackend.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final BudgetService budgetService;

    //CRUD só projetos
    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll() {
        List<ProjectDto> projectDtos = this.projectService.findAll();
        return ResponseEntity.ok().body(projectDtos);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> findOne(@PathVariable("projectId") String projectId) {
        ProjectDto project = this.projectService.findOne(projectId);
        return ResponseEntity.ok().body(project);
    }

    @PostMapping("/admin/{adminId}")
    public ResponseEntity<ProjectDto> create(@PathVariable("adminId") String adminId, @RequestBody InsertProjectDto projectDto) {
        ProjectDto project = this.projectService.create(adminId, projectDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(project.getId()).toUri();
        return ResponseEntity.created(uri).body(project);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> update(@PathVariable("projectId") String projectId, @RequestBody UpdateProjectDto projectDto) {
        ProjectDto project = this.projectService.update(projectId, projectDto);
        return ResponseEntity.ok().body(project);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteOne(@PathVariable("projectId") String projectId) {
        this.projectService.deleteOne(projectId);
        return ResponseEntity.noContent().build();
    }


    //CRUD Orçamentos

    /*
    Selecionar todos os budgets de um projeto

    @GetMapping("/{projectId}/budgets")
    public ResponseEntity<List<BudgetDto>> findAllBudgetsByProject(@PathVariable("projectId") String projectId) {
        List<BudgetDto> budgets = budgetService.findAll(projectId);
        return ResponseEntity.ok().body(budgets);
    }

    @GetMapping("/budget/{budgetId}")
    public ResponseEntity<BudgetDto> findBudget(@PathVariable("budgetId") String budgetId) {
        return ResponseEntity.ok().body(budgetService.findOne(budgetId));
    }


    @PostMapping("/budget/{projectId}")
    public ResponseEntity<BudgetDto> addBudget(@PathVariable("projectId") String projectId, @RequestBody InsertBudgetDto budgetDto) {
        BudgetDto budget = this.budgetService.create(projectId, budgetDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(projectId).toUri();
        return ResponseEntity.created(uri).body(budget);
    }
    @DeleteMapping("/budget/{budgetId}")
    public ResponseEntity<Void> deleteBudget(@PathVariable("budgetId") String budgetId) {
        budgetService.deleteOne(budgetId);
        return ResponseEntity.noContent().build();
    }
*/

    /*
    public ResponseEntity<String> addDocument() {
    }
    */

    //CRUD steps
    @PostMapping("/step/{projectId}")
    public ResponseEntity<ProjectDto> addStep(@PathVariable("projectId") String projectId, @RequestBody Step step) {
        ProjectDto project = this.projectService.addStep(projectId, step);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(project.getId()).toUri();
        return ResponseEntity.created(uri).body(project);
    }


    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ProjectDto>> findFromClient(@PathVariable("clientId") String clientId) {
        List<ProjectDto> project = this.projectService.findFromClient(clientId);

        return ResponseEntity.ok().body(project);
    }




    @DeleteMapping("/deletemany")
    public ResponseEntity<Void> deleteMany(@RequestBody List<String> ids) {
        this.projectService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }

}
