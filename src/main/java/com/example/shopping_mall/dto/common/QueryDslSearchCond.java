package com.example.shopping_mall.dto.common;

import com.example.shopping_mall.entity.enums.BrandName;
import com.example.shopping_mall.entity.enums.ProductType;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import static com.example.shopping_mall.entity.QProductEntity.productEntity;

public class QueryDslSearchCond {

    public static BooleanExpression searchBrandName(BrandName brandName) {

        if (Objects.nonNull(BrandName.searchForList(brandName))) {
            return productEntity.brandName.eq(brandName);
        }
        return null;
    }
    public static BooleanExpression searchProductType(ProductType productType) {

        if (Objects.nonNull(ProductType.searchForList(productType))) {
            return productEntity.productType.eq(productType);
        }
        return null;
    }

    public static BooleanExpression searchName(String name) {

        if (Objects.nonNull(name)) {
            return productEntity.name.contains(name);
        }
        return null;
    }

    public static BooleanExpression searchByDateRange(LocalDate startDtm, LocalDate endDtm) {
        if (!Objects.isNull(startDtm)) {
            LocalDateTime startDate = startDtm.atStartOfDay();
            LocalDateTime endDate = endDtm.atTime(LocalTime.MAX);

            return productEntity.createdDtm.between(startDate, endDate);
        }
        return null;
    }
}
