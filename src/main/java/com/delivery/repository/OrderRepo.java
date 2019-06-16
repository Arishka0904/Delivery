package com.delivery.repository;

import com.delivery.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findOrderById(Long id);


    Page<Order> findAllByStatusOrderByDateCreatedAsc(Integer orderStatus, Pageable pageable);


    Page<Order> findAllByUserIdOrderByStatusAscDateCreatedAsc(Long userId, Pageable pageable);

    Page<Order> findAllByOrderByStatusAscDateCreatedAsc(Pageable pageable);

    //Page<Order> findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeAsc(String buyerPhone, Pageable pageable);
}
