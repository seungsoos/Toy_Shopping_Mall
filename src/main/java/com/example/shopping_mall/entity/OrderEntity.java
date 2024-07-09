package com.example.shopping_mall.entity;

import com.example.shopping_mall.entity.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "tb_order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"accountEntity", "productOrderEntityList"})
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accountId")
    private AccountEntity accountEntity;


    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "orderEntity")
    private List<ProductOrderEntity> productOrderEntityList = new ArrayList<>();


    public void updateOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void settingProductOrderEntity(ProductOrderEntity productOrderEntity) {
        this.productOrderEntityList.add(productOrderEntity);
        accountEntity.settingOrderEntity(this);
    }


    public void settingAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
        accountEntity.settingOrderEntity(this);
    }

    @Builder
    public OrderEntity(Long quantity, OrderType orderType, AccountEntity accountEntity) {
        this.quantity = quantity;
        this.orderType = orderType;
        this.accountEntity = accountEntity;
    }

    public void updateQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public boolean isNotPreparing() {
        if (OrderType.PREPARING != this.orderType) {
            return true;
        }
        return false;
    }
}
