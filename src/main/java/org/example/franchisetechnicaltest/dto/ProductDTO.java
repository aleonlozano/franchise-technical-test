package org.example.franchisetechnicaltest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {
    private Long id;
    @NotBlank(message = "Product name is mandatory")
    private String name;
    @NotNull(message = "Stock is mandatory")
    @Min(value = 0, message = "Stock cannot be negative")
    private Long stock;
    private Long branchId;
    private String branchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Product name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Product name is mandatory") String name) {
        this.name = name;
    }

    public @NotNull(message = "Stock is mandatory") @Min(value = 0, message = "Stock cannot be negative") Long getStock() {
        return stock;
    }

    public void setStock(@NotNull(message = "Stock is mandatory") @Min(value = 0, message = "Stock cannot be negative") Long stock) {
        this.stock = stock;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}

