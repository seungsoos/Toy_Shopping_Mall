package com.example.shopping_mall.controller;

import com.example.shopping_mall.dto.order.request.OrderPurchaseDto;
import com.example.shopping_mall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    /**
     * 상품 조회 / 검색
     */

    /**
     * 상품상세보기
     */

    /**
     * 상품구매
     */
    @PostMapping("/purchase")
    public void purchase(@RequestBody OrderPurchaseDto orderPurchaseDto) {
        orderService.purchase(orderPurchaseDto);
    }

    /**
     * 상품 구매 수정 / 취소
     *
     * 수정시 OrderType - PREPARING 만 취소가 가능함.
     */

    /**
     * 상품 취소
     *
     * 취소시 OrderType - PREPARING 만 취소가 가능함.
     *
     */

}
