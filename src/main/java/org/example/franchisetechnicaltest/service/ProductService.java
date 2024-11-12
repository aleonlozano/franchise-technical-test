package org.example.franchisetechnicaltest.service;

import org.example.franchisetechnicaltest.dto.UpdateStockDTO;
import org.example.franchisetechnicaltest.exception.NotFoundException;
import org.example.franchisetechnicaltest.model.Product;
import org.example.franchisetechnicaltest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product updateProductStock(Long productId, UpdateStockDTO updateStockDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product", "id", productId));

        product.setStock(updateStockDTO.getStock());
        return productRepository.save(product);
    }
}
