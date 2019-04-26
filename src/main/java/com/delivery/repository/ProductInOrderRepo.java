package com.delivery.repository;

import com.delivery.domain.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepo extends JpaRepository<ProductInOrder, Long> {
}
