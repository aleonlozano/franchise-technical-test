package org.example.franchisetechnicaltest.repository;

import org.example.franchisetechnicaltest.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
