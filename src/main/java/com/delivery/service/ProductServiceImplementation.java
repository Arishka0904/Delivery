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
    public List<Product> findAllCurrentProduct() {
        return productRepo.findAllByCurrentVersionTrue();
    }

    @Override
    public void updateProductInDB(Product product) {

        Product productFromDB = productRepo.findProductById(product.getId());

        if (!product.equals(productFromDB)) {

            productFromDB.setCurrentVersion(false);
            productRepo.save(productFromDB);
            product.setId(null);
            addNewProductInDB(product);

        } else if (product.getQuantityInWarehouse() != productFromDB.getQuantityInWarehouse()) {

            productRepo.save(product);
        }
    }

    @Override
    public void addNewProductInDB(Product product) {
        product.setCurrentVersion(true);
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
