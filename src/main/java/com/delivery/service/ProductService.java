package com.delivery.service;

import com.delivery.domain.Category;
import com.delivery.domain.Product;
import com.delivery.domain.User;

import java.util.List;

public interface ProductService {

    List<Product> findAllCurrentProduct();

    Product findById(Long id);

    void updateProductInDB(Product product);

    void addNewProductInDB(Product product);

    boolean isProductExist(Product product);

    List<Product> findCurrentProductByCategory(Category category);

    void decreaseQuantityInWarehouse(Long productId, int amount);

    //decrease stock
    void increaseQuantityInWarehouse(Long productId, int amount);

}
