package com.example.shopping_mall.entity.enums;

import java.util.stream.Stream;

public enum ProductType {

    TOP("상의"),
    BOTTOM("하의"),
    SHOES("신발");

    private String desc;

    ProductType(String desc) {
        this.desc = desc;
    }


    public static ProductType searchForList(ProductType productType) {
        return Stream.of(ProductType.values())
                .filter(type -> type.equals(productType))
                .findFirst().orElse(null);
    }
}
