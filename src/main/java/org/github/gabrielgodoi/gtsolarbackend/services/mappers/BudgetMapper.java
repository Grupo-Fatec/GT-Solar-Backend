package org.github.gabrielgodoi.gtsolarbackend.services.mappers;

import org.github.gabrielgodoi.gtsolarbackend.dto.budget.BudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.InsertBudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Budget;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface BudgetMapper {
    Budget dtoToEntity(InsertBudgetDto budgetDto);
    BudgetDto entityToDto(Budget budget);
}