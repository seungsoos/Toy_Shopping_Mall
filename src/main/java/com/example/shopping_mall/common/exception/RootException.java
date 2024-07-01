package com.example.shopping_mall.common.exception;

import com.example.shopping_mall.common.ResultCodeType;
import lombok.Getter;

public class RootException extends RuntimeException{
    @Getter
   	private final ResultCodeType resultCodeType;

    public RootException(ResultCodeType resultCodeType) {
        this.resultCodeType = resultCodeType;
    }
}
