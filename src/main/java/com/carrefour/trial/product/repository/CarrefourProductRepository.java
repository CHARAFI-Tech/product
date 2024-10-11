package com.carrefour.trial.product.repository;

import com.carrefour.trial.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrefourProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);
}
