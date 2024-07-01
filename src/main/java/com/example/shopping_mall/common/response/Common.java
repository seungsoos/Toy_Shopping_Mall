package com.example.shopping_mall.common.response;

import com.example.shopping_mall.common.ResultCodeType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@ToString
public class Common {

    private final String code;
    private final String msg;

    public Common(ResultCodeType resultCodeType) {
        this.code = resultCodeType.getCode();
        this.msg = resultCodeType.getMsg();
    }

}
