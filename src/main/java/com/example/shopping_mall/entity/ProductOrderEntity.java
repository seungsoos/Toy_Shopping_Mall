package com.example.shopping_mall.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "tb_product_order")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOrderId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public void settingOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
    @Builder
    public ProductOrderEntity(ProductEntity productEntity, OrderEntity orderEntity) {
        this.productEntity = productEntity;
        this.orderEntity = orderEntity;
    }
}
