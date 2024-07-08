package com.example.shopping_mall.entity.enums;

public enum OrderType {
    PREPARING("상품 준비 중"),
    SHIPPING("배송 중"),
    DELIVERED("배송 완료");


    private String desc;

    OrderType(String desc) {
        this.desc = desc;
    }
}
