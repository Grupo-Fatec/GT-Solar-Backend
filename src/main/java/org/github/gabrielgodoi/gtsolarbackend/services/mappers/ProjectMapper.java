package org.github.gabrielgodoi.gtsolarbackend.services.mappers;

import org.github.gabrielgodoi.gtsolarbackend.dto.project.InsertProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.ProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {AdminMapper.class, ClientMapper.class, BudgetMapper.class})
public interface ProjectMapper {
    @Named("mapProjectToId")
    default String mapProjectToId(Project project) {
        return project != null ? project.getId() : null;
    }

    default String map(Project project) {
        return project != null ? project.getId() : null;
    }

    Project dtoToEntity(InsertProjectDto projectDto);

    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "client", target = "client")
    @Mapping(source = "budgetList", target = "budgetList")
    ProjectDto entityToDto(Project project);
}
