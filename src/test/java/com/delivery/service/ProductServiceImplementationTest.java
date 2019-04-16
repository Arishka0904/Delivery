package com.delivery.service;

import com.delivery.domain.Category;
import com.delivery.domain.Product;
import com.delivery.repository.ProductRepo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceImplementationTest {

    private ProductRepo productRepo = mock(ProductRepo.class);

    private ProductServiceImplementation productService = new ProductServiceImplementation(productRepo);
    private static final Product productFromDB = new Product();
    private static final Product product = new Product();


    @BeforeEach
    public void setUp() {
        productFromDB.setId(1l);
        productFromDB.setProductName("Product1");
        productFromDB.setCategory(Category.FIRST_CATEGORY);
        productFromDB.setPrice(new BigDecimal("9.99"));
        productFromDB.setWidth(1);
        productFromDB.setDepth(1);
        productFromDB.setHeight(1);
        productFromDB.setWeight(1);
        productFromDB.setQuantityOnPallet(1);
        productFromDB.setQuantityInWarehouse(100);
        productFromDB.setCurrentVersion(true);
        product.setId(1l);
        product.setProductName("Product1");
        product.setCategory(Category.FIRST_CATEGORY);
        product.setPrice(new BigDecimal("9.99"));
        product.setWidth(1);
        product.setDepth(1);
        product.setHeight(1);
        product.setWeight(1);
        product.setQuantityOnPallet(1);
        product.setQuantityInWarehouse(100);
        product.setCurrentVersion(true);

    }

    @Test
    void shouldSaveNothing() {
        when(productRepo.findProductById(productFromDB.getId())).thenReturn(product);

        productService.updateProductInDB(productFromDB);

        Mockito.verify(productRepo, Mockito.never()).save(any(Product.class));
    }

    @Test
    void shouldUpdateProduct() {
        product.setCurrentVersion(false);
        when(productRepo.findProductById(product.getId())).thenReturn(productFromDB);

        productService.updateProductInDB(product);

        Mockito.verify(productRepo, Mockito.times(1)).save(any(Product.class));
    }

    @Test
    void shouldUpdateAndSaveSaveNewProduct() {
        product.setWeight(2);
        when(productRepo.findProductById(product.getId())).thenReturn(productFromDB);

        productService.updateProductInDB(product);

        Mockito.verify(productRepo, Mockito.times(1)).save(productFromDB);
        Mockito.verify(productRepo, Mockito.times(1)).save(product);
    }

    @Test
    void shouldAddNewProductInDB() {
        product.setId(2l);
        productService.addNewProductInDB(product);
        Mockito.verify(productRepo, Mockito.times(1)).save(product);

    }

    @Test
    void shouldReturnTrueIfProductExist() {

        when(productRepo.findByProductName("Product1")).thenReturn(product);
        Assert.assertTrue(productService.isProductExist(product));
    }

    @Test
    void shouldReturnFalseIfProductExist() {
        when(productRepo.findByProductName("Product1")).thenReturn(null);
        Assert.assertFalse(productService.isProductExist(product));

    }

}