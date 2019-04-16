package com.delivery.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"products"})
@ToString(of = {"id", "address", "status", "createTime"})
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private OrderStatusEnum status;

    @CreationTimestamp
    private LocalDateTime createTime;

    @ElementCollection
    @CollectionTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "count")
    private Map<Product, Integer> products = new HashMap<>();

}
