package org.github.gabrielgodoi.gtsolarbackend.services.mappers;

import org.github.gabrielgodoi.gtsolarbackend.dto.budget.BudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.InsertBudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Budget;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface BudgetMapper {
    Budget dtoToEntity(InsertBudgetDto budgetDto);

    @Mapping(target = "project", expression = "java(org.github.gabrielgodoi.gtsolarbackend.services.mappers.CommonMapperUtils.mapProject(budget.getProject()))")
    BudgetDto entityToDto(Budget budget);
}
