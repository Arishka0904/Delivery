package com.delivery.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"quantityInWarehouse", "currentVersion"})
@ToString(of = {"id", "productName"})
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
    @Min(value = 1, message = "Should be more then 1")
    @Max(value = 2147483647, message = "Should be less then 2,147,483,647 ")
    private int width;

    @NotNull(message = "Depth can not be empty")
    @Min(value = 1, message = "Should be more then 1")
    @Max(value = 2147483647, message = "Should be less then 2,147,483,647 ")
    private int depth;

    @NotNull(message = "Height can not be empty")
    @Min(value = 1, message = "Should be more then 1")
    @Max(value = 2147483647, message = "Should be less then 2,147,483,647 ")
    private int height;

    @NotNull(message = "Weight can not be empty")
    @Min(value = 1, message = "Should be more then 1")
    @Max(value = 2147483647, message = "Should be less then 2,147,483,647 ")
    private int weight;

    @NotNull(message = "QuantityOnPallet can not be empty")
    @Min(value = 1, message = "Should be more then 1")
    @Max(value = 2147483647, message = "Should be less then 2,147,483,647 ")
    private int quantityOnPallet;

    @NotNull(message = "QuantityInWarehouse can not be empty")
    @Min(value = 1, message = "Should be more then 1")
    @Max(value = 2147483647, message = "Should be less then 2,147,483,647 ")
    private int quantityInWarehouse;

    private boolean currentVersion;

    @ColumnDefault("0")
    private Integer productStatus;
}
