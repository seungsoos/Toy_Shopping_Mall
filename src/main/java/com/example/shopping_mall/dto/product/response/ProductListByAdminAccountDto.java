package com.example.shopping_mall.dto.product.response;

import com.example.shopping_mall.entity.enums.BrandName;
import com.example.shopping_mall.entity.enums.ProductType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductListByAdminAccountDto {

    private Long accountId;
    private String loginId;
    private List<ProductAccountDto> productAccountDtos = new ArrayList<>();


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductAccountDto{
        private Long productId;
        private BrandName brandName;
        private String name;
        private ProductType productType;
        private Long quantity;
        private String information;
    }
}
