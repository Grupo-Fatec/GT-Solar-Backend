package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.InsertProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.ProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll() {
        List<ProjectDto> projects = this.projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable("id") String id) {
        ProjectDto project = this.projectService.findOne(id);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<ProjectDto>> findAllFromClient(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok().body(this.projectService.findFromClient(clientId));
    }

    @PostMapping("/{adminEmail}")
    public ResponseEntity<ProjectDto> create(@PathVariable("adminEmail") String adminEmail, @RequestBody InsertProjectDto projectDto) {
        ProjectDto created = this.projectService.create(adminEmail, projectDto);
        return ResponseEntity.created(URI.create("/projects/" + created.id())).body(created);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> update(@PathVariable("projectId") String id, @RequestBody InsertProjectDto projectDto) {
        return ResponseEntity.ok().body(this.projectService.update(id, projectDto));
    }

    @PatchMapping("/{projectId}/admin/{adminId}")
    public ResponseEntity<String> approvedByEngineer(@PathVariable("projectId") String projectId, @PathVariable("adminId") String adminId) {
        this.projectService.engineerApproveProject(projectId, adminId);
        return ResponseEntity.ok("Projeto com ID " + projectId + " foi aprovado pelo engenheiro.");
    }

    @PatchMapping("/{projectId}/client/{clientId}")
    public ResponseEntity<String> approvedByClient(@PathVariable("projectId") String projectId, @PathVariable("clientId") String clientId) {
        this.projectService.clientApproveProject(projectId, clientId);
        return ResponseEntity.ok("Projeto com ID " + projectId + " foi aprovado pelo cliente com ID " + clientId + ".");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.projectService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/many")
    public ResponseEntity<Void> deleteMany(@RequestBody List<String> ids) {
        this.projectService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}
