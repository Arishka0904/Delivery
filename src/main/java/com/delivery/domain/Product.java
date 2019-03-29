package com.delivery.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "productName", "quantityInWarehouse"})
@NoArgsConstructor

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ProductName can not be empty")
    private String productName;

    @NotNull(message = "Product category can not be empty")
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull(message = "Price can not be empty")
    private BigDecimal price;

    @NotNull(message = "Width can not be empty")
    private int width;

    @NotNull(message = "Depth can not be empty")
    private int depth;

    @NotNull(message = "Height can not be empty")
    private int height;

    @NotNull(message = "Weight can not be empty")
    private int weight;

    @NotNull(message = "QuantityOnPallet can not be empty")
    private int quantityOnPallet;

    @NotNull(message = "QuantityInWarehouse can not be empty")
    private int quantityInWarehouse;
}
