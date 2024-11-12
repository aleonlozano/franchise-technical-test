package org.example.franchisetechnicaltest.service;

import org.example.franchisetechnicaltest.dto.BranchDTO;
import org.example.franchisetechnicaltest.dto.FranchiseDTO;
import org.example.franchisetechnicaltest.model.Branch;
import org.example.franchisetechnicaltest.model.Franchise;
import org.example.franchisetechnicaltest.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    public FranchiseDTO saveFranchise(FranchiseDTO franchiseDTO) {
        Franchise franchise = convertToEntity(franchiseDTO);
        return convertToDto(franchiseRepository.save(franchise));
    }

    private FranchiseDTO convertToDto(Franchise franchise) {
        FranchiseDTO franchiseDto = new FranchiseDTO();
        franchiseDto.setId(franchise.getId());
        franchiseDto.setName(franchise.getName());
        if (franchise.getBranches() != null) {
            franchiseDto.setBranches(franchise.getBranches().stream()
                    .map(branch -> {
                        BranchDTO branchDTO = new BranchDTO();
                        branchDTO.setId(branch.getId());
                        branchDTO.setName(branch.getName());
                        branchDTO.setFranchiseId(franchise.getId());
                        return branchDTO;
                    }).collect(Collectors.toList()));
        }
        return franchiseDto;
    }

    private Franchise convertToEntity(FranchiseDTO franchiseDto) {
        Franchise franchise = new Franchise();
        franchise.setName(franchiseDto.getName());
        if (franchiseDto.getBranches() != null) {
            franchise.setBranches(franchiseDto.getBranches().stream()
                    .map(branchDTO -> {
                        Branch branch = new Branch();
                        branch.setName(branchDTO.getName());
                        branch.setFranchise(franchise);
                        return branch;
                    }).collect(Collectors.toList()));
        }
        return franchise;
    }
}
