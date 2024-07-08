package com.example.shopping_mall.service;

import com.example.shopping_mall.dto.order.request.OrderPurchaseDto;

public interface OrderService {
    void purchase(OrderPurchaseDto orderPurchaseDto);
}
