package com.delivery.service;

import com.delivery.domain.Product;
import com.delivery.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService{

private ProductRepo productRepo;

    public ProductServiceImplementation(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public void saveProduct(Product product) {

        productRepo.save(product); // TODO: 21.03.2019 Можно переписать, сравнивать каждое поле с прерыдущим значением, и сохранять только если изменилось
    }


}
