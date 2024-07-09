package com.example.shopping_mall.dto.order.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateDto {

    private Long orderId;
    private Long quantity;
}
