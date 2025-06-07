package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.InsertProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.ProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<ProjectDto> findAll() {

    }

    public ProjectDto findOne(String id) {

    }

    public List<ProjectDto> findFromClient(String clientId) {

    }


    public ProjectDto create(String adminId, InsertProjectDto projectDto) {

    }


    public ProjectDto update(String projectId, InsertProjectDto updateDto) {

    }

    public ProjectDto addStep(String projectId, Step step) {

    }

    public void deleteOne(String projectId) {

    }

    public void deleteMany(List<String> projectIds) {

    }

}
