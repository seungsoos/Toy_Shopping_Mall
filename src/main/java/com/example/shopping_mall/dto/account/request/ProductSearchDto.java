package com.example.shopping_mall.dto.account.request;

import com.example.shopping_mall.entity.enums.BrandName;
import com.example.shopping_mall.entity.enums.ProductType;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductSearchDto {

    private Long accountId;
    private BrandName brandName;
    private String name;
    private ProductType productType;
    private LocalDate startDtm;
    private LocalDate endDtm;
    private Integer viewPage;
    private Integer viewCount;

}
