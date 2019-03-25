package com.delivery.service;

import com.delivery.domain.Product;
import com.delivery.domain.User;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public void saveProductInDB(Product product);

    public boolean isProductExist(Product product);
}
