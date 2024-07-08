package com.example.shopping_mall.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPurchaseDto {

    private Long productId;
    private Long quantity;
    private Long accountId;
}
