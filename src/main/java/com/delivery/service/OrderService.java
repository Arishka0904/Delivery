package com.delivery.service;

import com.delivery.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created By Zhu Lin on 3/14/2018.
 */
@Service
public interface OrderService {
    Page<Order> findAll(Pageable pageable);

    Page<Order> findByStatus(Integer status, Pageable pageable);

    Page<Order> findByBuyerID(Long buyerId, Pageable pageable);

//    Page<Order> findByBuyerEmail(String email, Pageable pageable);
//
//    Page<Order> findByBuyerPhone(String phone, Pageable pageable);

    Order findOne(Long orderId);

    void finish(Long orderId);

    void  cancel(Long orderId);

}
