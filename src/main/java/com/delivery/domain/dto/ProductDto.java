package com.delivery.domain.dto;

import com.delivery.domain.Category;
import com.delivery.domain.Product;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"quantityInWarehouse", "isCurrentVersion"})
@ToString(of = {"id", "productName", "quantityInWarehouse"})
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String productName;
    private Category category;
    private BigDecimal price;
    private int width;
    private int depth;
    private int height;
    private int weight;
    private int quantityOnPallet;
    private int quantityInWarehouse;
    private boolean currentVersion;

    public Product buildProduct() {
        Product product = new Product();
        product.setId(id);
        product.setProductName(productName);
        product.setCategory(category);
        product.setPrice(price);
        product.setWidth(width);
        product.setDepth(depth);
        product.setHeight(height);
        product.setWeight(weight);
        product.setQuantityOnPallet(quantityOnPallet);
        product.setQuantityInWarehouse(quantityInWarehouse);
        product.setCurrentVersion(currentVersion);
        return product;
    }
}
