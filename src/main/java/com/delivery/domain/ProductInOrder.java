package com.delivery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"order"})
@ToString(of = {"id", "product", "quantity"})
@NoArgsConstructor
@Entity
public class ProductInOrder extends AbstractDomainClass {

    @OneToOne
    private Product product;

    private Integer quantity;

    @ManyToOne
    private Order order;

    public ProductInOrder(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
