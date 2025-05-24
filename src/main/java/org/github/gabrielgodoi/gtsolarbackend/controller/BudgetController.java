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

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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

    @PostMapping
    public ResponseEntity<BudgetDto> create(
            @PathVariable String projectId,
            @Valid @RequestBody InsertBudgetDto insertBudgetDto) {

        BudgetDto createdBudget = budgetService.create(projectId, insertBudgetDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/budgets/{id}")
                .buildAndExpand(createdBudget.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdBudget);
    }

  /*  @PutMapping("/{id}")
    public ResponseEntity<BudgetDto> update(
            @PathVariable String id,
            @RequestBody UpdateBudgetDto budgetDto) {

        BudgetDto updatedBudget = budgetService.update(id, budgetDto);
        return ResponseEntity.ok(updatedBudget);
    }*/

   /* @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        budgetService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }*/
}
