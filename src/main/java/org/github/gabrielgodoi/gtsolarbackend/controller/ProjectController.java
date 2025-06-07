package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.services.ProjectService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
}
