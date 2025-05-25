package org.github.gabrielgodoi.gtsolarbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.BudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.InsertBudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.services.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/budget")
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping
    public ResponseEntity<List<BudgetDto>> findAll() {
        List<BudgetDto> budgets = budgetService.findAll();
        return ResponseEntity.ok(budgets);
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<BudgetDto> create(
            @PathVariable("projectId") String projectId,
            @Valid @RequestBody InsertBudgetDto insertBudgetDto) {

        BudgetDto createdBudget = budgetService.create(projectId, insertBudgetDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/budgets/{id}")
                .buildAndExpand(createdBudget.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdBudget);
    }
}
