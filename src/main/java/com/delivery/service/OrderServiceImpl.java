package com.delivery.service;

import com.delivery.domain.Order;
import com.delivery.domain.Product;
import com.delivery.domain.ProductInOrder;
import com.delivery.enums.OrderStatusEnum;
import com.delivery.enums.ResultEnum;
import com.delivery.exception.MyException;
import com.delivery.repository.OrderRepo;
import com.delivery.repository.ProductInOrderRepo;
import com.delivery.repository.ProductRepo;
import com.delivery.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepo orderRepo;

    private UserRepo userRepo;

    private ProductRepo productRepo;

    private ProductService productService;

    private ProductInOrderRepo productInOrderRepo;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepo.findAllByOrderByStatusAscDateCreatedAsc(pageable);
    }

    @Override
    public Page<Order> findByStatus(Integer status, Pageable pageable) {
        return orderRepo.findAllByStatusOrderByDateCreatedAsc(status, pageable);
    }

    @Override
    public Page<Order> findByBuyerID(Long buyerId, Pageable pageable) {
        return orderRepo.findAllByUserIdOrderByStatusAscDateCreatedAsc(buyerId, pageable);
    }

    @Override
    public Order findOne(Long orderId) {
        Order orderMain = orderRepo.findOrderById(orderId);
        if(orderMain == null) {
            throw new MyException(ResultEnum.ORDER_NOT_FOUND);
        }
        return orderMain;
    }

    @Override
    @Transactional
    public void finish(Long orderId) {
        Order orderMain = findOne(orderId);
        if(!orderMain.getStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setStatus(OrderStatusEnum.FINISHED.getCode());
        orderRepo.save(orderMain);
    }

    @Override
    @Transactional
    public void cancel(Long orderId) {
        Order order = findOne(orderId);
        if(!order.getStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        order.setStatus(OrderStatusEnum.CANCELED.getCode());
        orderRepo.save(order);

        // Restore Stock
        Iterable<ProductInOrder> products = order.getProductsInOrder();
        for(ProductInOrder productInOrder : products) {
            Product product = productRepo.findProductById(productInOrder.getId());
            if(product != null) {
                productService.increaseQuantityInWarehouse(productInOrder.getId(), productInOrder.getQuantity());
            }
        }
    }

    @Autowired
    public void setOrderRepo(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductInOrderRepo(ProductInOrderRepo productInOrderRepo) {
        this.productInOrderRepo = productInOrderRepo;
    }
}
