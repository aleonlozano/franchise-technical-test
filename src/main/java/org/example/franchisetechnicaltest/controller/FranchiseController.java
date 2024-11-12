package org.example.franchisetechnicaltest.controller;

import jakarta.validation.Valid;
import org.example.franchisetechnicaltest.dto.BranchDTO;
import org.example.franchisetechnicaltest.dto.FranchiseDTO;
import org.example.franchisetechnicaltest.dto.ProductStockDTO;
import org.example.franchisetechnicaltest.service.FranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchises")
@Validated
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FranchiseDTO addFranchise(@Valid @RequestBody FranchiseDTO franchiseDTO) {
        return franchiseService.saveFranchise(franchiseDTO);
    }

    @PostMapping("/{franchiseId}/branches")
    public BranchDTO addBranchToFranchise(
            @PathVariable Long franchiseId,
            @Valid @RequestBody BranchDTO branchDTO) {
        return franchiseService.addBranchToFranchise(franchiseId, branchDTO);
    }

    @GetMapping("/{franchiseId}/top-stock-products")
    public List<ProductStockDTO> getTopStockProductByBranch(
            @PathVariable Long franchiseId) {
        return franchiseService.getTopStockProductByBranch(franchiseId);
    }
}
