package com.delivery.service;

import com.delivery.domain.User;
import com.delivery.domain.dto.ProductDto;
import com.delivery.form.ItemForm;

import java.math.BigDecimal;
import java.util.Collection;

public interface CartService {
    void addItem(ItemForm itemForm);
    void removeItem(Long productId);
    void updateQuantity(Long productId, Integer quantity);

    Collection<ProductDto> findAll();

    void  checkout(User user, String address);

    BigDecimal getTotal();

}
