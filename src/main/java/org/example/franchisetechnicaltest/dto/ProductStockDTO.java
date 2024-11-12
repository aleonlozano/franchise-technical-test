package org.example.franchisetechnicaltest.dto;

public class ProductStockDTO {
    private String branchName;
    private String productName;
    private Long stock;

    // Constructor
    public ProductStockDTO(String branchName, String productName, Long stock) {
        this.branchName = branchName;
        this.productName = productName;
        this.stock = stock;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
