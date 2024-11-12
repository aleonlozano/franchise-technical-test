package org.example.franchisetechnicaltest.repository;

import org.example.franchisetechnicaltest.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    Optional<Franchise> findByName(String name);
}
