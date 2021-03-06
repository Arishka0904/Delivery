package com.delivery.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"products"})
@ToString(of = {"id", "address", "status"})
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractDomainClass {

    private String address;

    @NotNull
    @ColumnDefault("0")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Order(String address, @NotNull Integer status, User user) {
        this.address = address;
        this.status = status;
        this.user = user;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<ProductInOrder> productsInOrder = new ArrayList<>();

    // Total Amount
    @NotNull
    private BigDecimal orderAmount;
}
