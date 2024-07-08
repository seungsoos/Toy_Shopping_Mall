package com.example.shopping_mall.dto.order.response;

import com.example.shopping_mall.entity.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDto {

    private Long orderId;
    private Long quantity;
    private OrderType orderType;
    private LocalDateTime createdDtm;
    private LocalDateTime modifiedDtm;
}
