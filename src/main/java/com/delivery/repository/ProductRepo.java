package com.delivery.repository;

import com.delivery.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByProductName(String productName);
}
