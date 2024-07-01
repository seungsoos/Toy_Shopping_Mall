package com.example.shopping_mall.config;

import com.example.shopping_mall.common.response.SuccessResponseAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class SuccessBodyAdvice extends SuccessResponseAdvice {
}
