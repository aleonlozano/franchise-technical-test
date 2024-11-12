package org.example.franchisetechnicaltest.repository;

import org.example.franchisetechnicaltest.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
