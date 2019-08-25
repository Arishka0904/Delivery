package com.delivery.service;

import com.delivery.domain.Category;
import com.delivery.domain.Product;
import com.delivery.enums.ResultEnum;
import com.delivery.exception.MyException;
import com.delivery.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductServiceImplementation implements ProductService {

    private ProductRepo productRepo;

    public ProductServiceImplementation(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAllCurrentProduct() {
        return productRepo.findAllByCurrentVersionTrue();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findProductById(id);
    }

    @Override
    public void updateProductInDB(Product product) {

        Product productFromDB = productRepo.findProductById(product.getId());

        if (!product.equals(productFromDB)) {

            productFromDB.setCurrentVersion(false);
            productRepo.save(productFromDB);
            product.setId(null);
            addNewProductInDB(product);

        } else if ((product.getQuantityInWarehouse() != productFromDB.getQuantityInWarehouse()) ||
                (product.isCurrentVersion() != productFromDB.isCurrentVersion())) {
            productRepo.save(product);
        }
    }

    @Override
    public void addNewProductInDB(Product product) {

        productRepo.save(product);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isProductExist(Product product) {

        Product productFromDb = productRepo.findByProductName(product.getProductName());

        return productFromDb != null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findCurrentProductByCategory(Category category) {

        return productRepo.findAllByCategoryAndCurrentVersionTrue(category);
    }

    @Override
    @Transactional
    public void decreaseQuantityInWarehouse(Long productId, int amount) {
        Product product = findById(productId);
        if (product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = product.getQuantityInWarehouse() - amount;
        if(update <= 0) throw new MyException(ResultEnum.PRODUCT_NOT_ENOUGH );

        product.setQuantityInWarehouse(update);
        productRepo.save(product);
    }

    @Override
    public void increaseQuantityInWarehouse(Long productId, int amount) {
        Product product = findById(productId);
        if (product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = product.getQuantityInWarehouse() + amount;
        product.setQuantityInWarehouse(update);
        productRepo.save(product);
    }


}
