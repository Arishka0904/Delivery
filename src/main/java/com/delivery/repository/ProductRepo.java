package com.delivery.repository;

import com.delivery.domain.Category;
import com.delivery.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findByProductName(String productName);

    Product findProductById(Long id);

    List<Product> findAllByCurrentVersionTrue();

    List<Product> findAllByCategoryAndCurrentVersionTrue(Category category);

}
