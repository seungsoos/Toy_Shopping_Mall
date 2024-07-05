package com.example.shopping_mall.dto.product.response;

import com.example.shopping_mall.entity.enums.BrandName;
import com.example.shopping_mall.entity.enums.ProductType;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDetailDto {

    private Long productId;
    private BrandName brandName;
    private String name;
    private ProductType productType;
    private Long quantity;
    private String information;
    private String imageUrl;

}
