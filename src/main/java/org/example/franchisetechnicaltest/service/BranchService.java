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

        Product savedProduct = productRepository.save(product);

        branch.getProducts().add(savedProduct);

        branchRepository.save(branch);

        productDTO.setId(savedProduct.getId());
        productDTO.setBranchId(branchId);
        productDTO.setBranchName(branch.getName());

        return productDTO;
    }

    @Transactional
    public void removeProductFromBranch(Long branchId, Long productId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch",  "id", branchId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product",  "id", productId));

        if (branch.getProducts().contains(product)) {
            branch.getProducts().remove(product);
            branchRepository.save(branch);
        } else {
            throw new RuntimeException("Product is not associated with the specified branch");
        }
    }
}
