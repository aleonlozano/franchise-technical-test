package org.example.franchisetechnicaltest.controller;

import org.example.franchisetechnicaltest.dto.ProductDTO;
import org.example.franchisetechnicaltest.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/{branchId}/products")
    public ProductDTO addProductToBranch(
            @PathVariable Long branchId,
            @Valid @RequestBody ProductDTO productDTO) {
        return branchService.addProductToBranch(branchId, productDTO);
    }

    @DeleteMapping("{branchId}/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductFromBranch(
            @PathVariable Long branchId,
            @PathVariable Long productId) {
        branchService.removeProductFromBranch(branchId, productId);
    }
}
