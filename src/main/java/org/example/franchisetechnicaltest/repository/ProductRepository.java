package org.example.franchisetechnicaltest.repository;

import org.example.franchisetechnicaltest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN p.branches b WHERE b.franchise.id = :franchiseId " +
            "AND p.stock = (SELECT MAX(p2.stock) FROM Product p2 JOIN p2.branches b2 WHERE b2 = b)")
    List<Product> findTopStockProductsByFranchise(@Param("franchiseId") Long franchiseId);
}
