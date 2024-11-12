package org.example.franchisetechnicaltest.service;

import jakarta.transaction.Transactional;
import org.example.franchisetechnicaltest.dto.BranchDTO;
import org.example.franchisetechnicaltest.dto.FranchiseDTO;
import org.example.franchisetechnicaltest.dto.ProductStockDTO;
import org.example.franchisetechnicaltest.exception.ExistingFranchiseException;
import org.example.franchisetechnicaltest.exception.NotFoundException;
import org.example.franchisetechnicaltest.model.Branch;
import org.example.franchisetechnicaltest.model.Franchise;
import org.example.franchisetechnicaltest.model.Product;
import org.example.franchisetechnicaltest.repository.BranchRepository;
import org.example.franchisetechnicaltest.repository.FranchiseRepository;
import org.example.franchisetechnicaltest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ProductRepository productRepository;

    public FranchiseDTO saveFranchise(FranchiseDTO franchiseDTO) {
        franchiseRepository.findByName(franchiseDTO.getName())
                .ifPresent(existingFranchise -> {
                    throw new ExistingFranchiseException(franchiseDTO.getName());
                });

        Franchise franchise = franchiseRepository.save(convertToEntity(franchiseDTO));

        return convertToDto(franchise);
    }

    @Transactional
    public BranchDTO addBranchToFranchise(Long franchiseId, BranchDTO branchDTO) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new NotFoundException("Franchise", "id", franchiseId));

        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setFranchise(franchise);

        Branch savedBranch = branchRepository.save(branch);

        branchDTO.setId(savedBranch.getId());
        branchDTO.setFranchiseId(franchiseId);

        return branchDTO;
    }

    public List<ProductStockDTO> getTopStockProductByBranch(Long franchiseId) {
        if (!franchiseRepository.existsById(franchiseId)) {
            throw new NotFoundException("Franchise", "id", franchiseId);
        }

        List<Product> topStockProducts = productRepository.findTopStockProductsByFranchise(franchiseId);

        return topStockProducts.stream()
                .map(product -> new ProductStockDTO(
                        product.getBranches().stream()
                                .filter(b -> b.getFranchise().getId().equals(franchiseId))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Branch not found for franchise"))
                                .getName(),
                        product.getName(),
                        product.getStock()))
                .collect(Collectors.toList());

    }

    private FranchiseDTO convertToDto(Franchise franchise) {
        if (franchise == null) {
            return null;
        }

        FranchiseDTO franchiseDto = new FranchiseDTO();
        franchiseDto.setId(franchise.getId());
        franchiseDto.setName(franchise.getName());
        return franchiseDto;
    }

    private Franchise convertToEntity(FranchiseDTO franchiseDto) {
        if (franchiseDto == null) {
            return null;
        }

        Franchise franchise = new Franchise();
        franchise.setName(franchiseDto.getName());
        return franchise;
    }
}
