package com.example.shopping_mall.entity.enums;

import java.util.stream.Stream;

public enum BrandName {

    NIKE("나이키"),
    ADIDAS("아디다스"),
    ASICS("아식스"),
    HDEX("에이치덱스");

    private String desc;

    BrandName(String desc) {
        this.desc = desc;
    }

    public static BrandName searchForList(BrandName brandName) {
        return Stream.of(BrandName.values())
                .filter(type -> type.equals(brandName))
                .findFirst().orElse(null);
    }

}
