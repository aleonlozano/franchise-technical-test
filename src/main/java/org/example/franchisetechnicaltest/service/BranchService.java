package org.example.franchisetechnicaltest.service;

import org.example.franchisetechnicaltest.dto.ProductDTO;
import org.example.franchisetechnicaltest.exception.NotFoundException;
import org.example.franchisetechnicaltest.model.Branch;
import org.example.franchisetechnicaltest.model.Product;
import org.example.franchisetechnicaltest.repository.BranchRepository;
import org.example.franchisetechnicaltest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductDTO addProductToBranch(Long branchId, ProductDTO productDTO) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch", "id", branchId));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setStock(productDTO.getStock());
        product.setBranch(branch);

        Product savedProduct = productRepository.save(product);

        productDTO.setId(savedProduct.getId());
        productDTO.setBranchId(branchId);

        return productDTO;
    }
}
