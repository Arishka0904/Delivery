package com.delivery.service;

import com.delivery.domain.Product;
import com.delivery.domain.User;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public void saveProduct(Product product);
}
