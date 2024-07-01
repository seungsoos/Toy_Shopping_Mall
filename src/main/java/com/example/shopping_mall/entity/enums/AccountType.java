package com.example.shopping_mall.entity.enums;

public enum AccountType {

    ADMIN("관리자"),

    USER("사용자");

    private String desc;

    AccountType(String desc) {
        this.desc = desc;
    }
}
