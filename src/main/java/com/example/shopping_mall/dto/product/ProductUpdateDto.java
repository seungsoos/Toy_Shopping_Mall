package com.example.shopping_mall.dto.product;

import com.example.shopping_mall.entity.enums.BrandName;
import com.example.shopping_mall.entity.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {

    private Long productId;

    private BrandName brandName;

    private String name;

    private ProductType productType;

    private Long quantity;

    private String information;
}
