package org.example.franchisetechnicaltest.controller;

import org.example.franchisetechnicaltest.dto.UpdateStockDTO;
import org.example.franchisetechnicaltest.model.Product;
import org.example.franchisetechnicaltest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateProductStock(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateStockDTO updateStockDTO) {
        Product updatedProduct = productService.updateProductStock(productId, updateStockDTO);
        return ResponseEntity.ok(updatedProduct);
    }
}
