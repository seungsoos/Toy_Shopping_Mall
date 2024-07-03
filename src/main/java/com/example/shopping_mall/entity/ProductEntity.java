package com.example.shopping_mall.entity;

import com.example.shopping_mall.dto.product.request.ProductCreateDto;
import com.example.shopping_mall.dto.product.request.ProductUpdateDto;
import com.example.shopping_mall.entity.enums.BrandName;
import com.example.shopping_mall.entity.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

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

    @Builder
    public ProductEntity(ProductCreateDto productCreateDto, AccountEntity accountEntity, String imageUrl) {
        this.brandName = productCreateDto.getBrandName();
        this.name = productCreateDto.getName();
        this.productType = productCreateDto.getProductType();
        this.quantity = productCreateDto.getQuantity();
        this.information = productCreateDto.getInformation();
        this.accountEntity = accountEntity;
        this.imageUrl = imageUrl;
    }

    public static ProductEntity toEntity(ProductCreateDto productCreateDto, AccountEntity accountEntity, String imageUrl) {
        return new ProductEntity(productCreateDto, accountEntity, imageUrl);
    }

    public void settingAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
        accountEntity.settingProductEntity(this);

    }

    public void settingProductOrderEntity(ProductOrderEntity productOrderEntity) {
        productOrderEntity.setProductEntity(this);
        this.productOrderEntityList.add(productOrderEntity);

    }


    public void update(ProductUpdateDto productUpdateDto, String imageUrl) {
        this.brandName = productUpdateDto.getBrandName();
        this.name = productUpdateDto.getName();
        this.productType = productUpdateDto.getProductType();
        this.quantity = productUpdateDto.getQuantity();
        this.information = productUpdateDto.getInformation();
        this.imageUrl = imageUrl;
    }
}
