package com.example.shopping_mall.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ResultCodeType {

    // 공통
    SUCCESS("2S000000", "성공"),
    SERVER_ERROR_5S000000("5S000000", "서버 내부 오류"),
    SERVER_ERROR_4S000000("4S000000", "비정상적인 요청"),
    SERVER_ERROR_EXISTS_LOGIN_ID("4S000001", "이미 존재하는 아이디 입니다."),
    SERVER_ERROR_EXISTS_EMAIL("4S000002", "이미 존재하는 이메일 입니다."),
    SERVER_ERROR_EXISTS_NICK_NAME("4S000003", "이미 존재하는 닉네임 입니다."),
    SERVER_ERROR_LOGIN_ID_NOT_EQUALS("4S000004", "존재하지 않는 아이디입니다."),
    SERVER_ERROR_REQUEST_QUANTITY("4S000005", "상품의 수량보다 더 많은 요청입니다."),


    JWT_TOKEN_ERROR("4S000100",  "유효하지않는 토큰입니다."),
    JWT_TOKEN_EXPIRED("4S000100",  "만료된 토큰입니다.");


    private final String code;
    private final String msg;

    ResultCodeType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
