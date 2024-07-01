package com.example.shopping_mall.entity.enums;

public enum BrandName {

    NIKE("나이키"),
    ADIDAS("아디다스"),
    ASICS("아식스"),
    HDEX("에이치덱스");

    private String desc;

    BrandName(String desc) {
        this.desc = desc;
    }
}
