package com.delivery.service;

import com.delivery.domain.Product;
import com.delivery.domain.User;

import java.util.List;

public interface ProductService {

    public List<Product> findAllCurrentProduct();

    public void updateProductInDB(Product product);

    public void addNewProductInDB(Product product);

    public boolean isProductExist(Product product);
}
