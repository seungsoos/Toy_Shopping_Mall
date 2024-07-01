package com.example.shopping_mall.entity;

import com.example.shopping_mall.entity.enums.BrandName;
import com.example.shopping_mall.entity.enums.ProductType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "tb_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BrandName brandName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private String information;

    private String imageUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accountId")
    private AccountEntity accountEntity;

    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "productEntity")
    private List<ProductOrderEntity> productOrderEntityList = new ArrayList<>();
}
