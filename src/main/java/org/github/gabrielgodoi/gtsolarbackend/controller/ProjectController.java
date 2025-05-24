package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.BudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.InsertBudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.InsertProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.ProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.UpdateProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
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

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll() {
        List<ProjectDto> projectDtos = this.projectService.findAll();
        return ResponseEntity.ok().body(projectDtos);
    }

    @PostMapping("/admin/{adminId}")
    public ResponseEntity<ProjectDto> create(@PathVariable("adminId") String adminId, @RequestBody InsertProjectDto projectDto) {
        ProjectDto project = this.projectService.create(adminId, projectDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(project.getId()).toUri();
        return ResponseEntity.created(uri).body(project);
    }

    @PutMapping("/{projectdId}")
    public ResponseEntity<ProjectDto> update(@PathVariable("projectdId") String projectId, @RequestBody UpdateProjectDto projectDto) {
        ProjectDto project = this.projectService.update(projectId, projectDto);
        return ResponseEntity.ok().body(project);
    }

    /*
    public ResponseEntity<String> addDocument() {
    }
    */

    @PostMapping("/step/{projectdId}")
    public ResponseEntity<ProjectDto> addStep(@PathVariable("projectdId") String projectId, @RequestBody Step step) {
        ProjectDto project = this.projectService.addStep(projectId, step);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(project.getId()).toUri();
        return ResponseEntity.created(uri).body(project);
    }

   /*
   *  @PostMapping("/budget/{projectdId}")
    public ResponseEntity<BudgetDto> addBudget(@PathVariable("projectdId") String projectId, @RequestBody InsertBudgetDto budgetDto) {
        BudgetDto budget = this.projectService.addBudget(projectId, budgetDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(projectId).toUri();
        return ResponseEntity.created(uri).body(budget);
    }
   *
   * */

    @GetMapping("/{projectdId}")
    public ResponseEntity<ProjectDto> findOne(@PathVariable("projectdId") String projectId) {
        ProjectDto project = this.projectService.findOne(projectId);
        return ResponseEntity.ok().body(project);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ProjectDto>> findFromClient(@PathVariable("clientId") String clientId) {
        List<ProjectDto> project = this.projectService.findFromClient(clientId);

        return ResponseEntity.ok().body(project);
    }


    @DeleteMapping("/{projectdId}")
    public ResponseEntity<Void> deleteOne(@PathVariable("projectdId") String projectId) {
        this.projectService.deleteOne(projectId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletemany")
    public ResponseEntity<Void> deleteMany(@RequestBody List<String> ids) {
        this.projectService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}
