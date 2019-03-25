package com.delivery.service;

import com.delivery.domain.Product;
import com.delivery.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    private ProductRepo productRepo;

    public ProductServiceImplementation(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public void saveProductInDB(Product product) {

        productRepo.save(product);
    }

    @Override
    public boolean isProductExist(Product product) {

        Product productFromDb = productRepo.findByProductName(product.getProductName());

        if (productFromDb == null) {
            return false;
        }
        return true;
    }
}
