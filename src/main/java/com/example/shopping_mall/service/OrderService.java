package com.example.shopping_mall.service;

import com.example.shopping_mall.dto.order.request.OrderPurchaseDto;
import com.example.shopping_mall.dto.order.response.OrderDetailDto;

public interface OrderService {
    void purchase(OrderPurchaseDto orderPurchaseDto);

    OrderDetailDto detail(Long orderId, Long accountId);
}
