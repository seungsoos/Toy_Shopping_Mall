package com.example.shopping_mall.common.exception;

import com.example.shopping_mall.common.ResultCodeType;
import com.example.shopping_mall.common.response.Common;
import com.example.shopping_mall.common.response.CommonResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = RootException.class)
    public ResponseEntity<CommonResponseDto<Object>> rootException(HttpServletRequest request, RootException rootException) {
        log.error("> [Request] : {}", request.getRequestURI());
        Common common = new Common(rootException.getResultCodeType());
        return ResponseEntity.ok(new CommonResponseDto<>(common));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResponseDto<Object>> exception(HttpServletRequest request) {
        log.error("> [Request] : {}", request.getRequestURI());
        Common common = new Common(ResultCodeType.SERVER_ERROR_5S000000);
        return ResponseEntity.ok(new CommonResponseDto<>(common));
    }
}
