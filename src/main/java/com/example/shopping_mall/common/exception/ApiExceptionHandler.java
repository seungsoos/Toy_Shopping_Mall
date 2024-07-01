package com.example.shopping_mall.common.exception;

import com.example.shopping_mall.common.ResultCodeType;
import com.example.shopping_mall.common.response.Common;
import com.example.shopping_mall.common.response.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    @ExceptionHandler(value = RootException.class)
    public CommonResponseDto rootException(Exception ex) {
        return new CommonResponseDto(new Common(ResultCodeType.SERVER_ERROR_4S000000), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public CommonResponseDto exception(Exception ex) {
        return new CommonResponseDto(new Common(ResultCodeType.SERVER_ERROR_5S000000), ex.getMessage());
    }
}
