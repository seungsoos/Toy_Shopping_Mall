package com.example.shopping_mall.entity;

import com.example.shopping_mall.entity.enums.OrderType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "tb_order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

}
