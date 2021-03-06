package com.delivery.domain.dto;

import com.delivery.domain.Category;
import com.delivery.domain.Product;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(of = {"id", "productName"})
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
    private int quantity;

    public ProductDto(Product product, int quantity) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.weight = product.getWeight();
        this.depth = product.getHeight();
        this.quantityOnPallet = product.getQuantityOnPallet();
        this.quantityInWarehouse = product.getQuantityInWarehouse();
        this.currentVersion = product.isCurrentVersion();
        this.quantity = quantity;
    }

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
