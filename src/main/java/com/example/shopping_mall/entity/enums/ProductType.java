package com.example.shopping_mall.entity.enums;

public enum ProductType {

    TOP("상의"),
    BOTTOM("하의"),
    SHOES("신발");

    private String desc;

    ProductType(String desc) {
        this.desc = desc;
    }
}
