package com.example.shopping_mall.service;

import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.order.request.OrderCancelDto;
import com.example.shopping_mall.dto.order.request.OrderPurchaseDto;
import com.example.shopping_mall.dto.order.request.OrderUpdateDto;
import com.example.shopping_mall.dto.order.response.OrderDetailDto;
import com.example.shopping_mall.dto.product.response.ProductListDto;
import org.springframework.data.domain.Page;

public interface OrderService {
    void purchase(OrderPurchaseDto orderPurchaseDto);

    OrderDetailDto detail(Long orderId, Long accountId);

    void update(OrderUpdateDto orderUpdateDto);

    void cancel(OrderCancelDto orderCancelDto);

    Page<ProductListDto> list(ProductSearchDto productSearchDto);
}
