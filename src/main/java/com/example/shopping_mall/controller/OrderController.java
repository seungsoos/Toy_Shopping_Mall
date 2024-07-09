package com.example.shopping_mall.controller;

import com.example.shopping_mall.dto.order.request.OrderCancelDto;
import com.example.shopping_mall.dto.order.request.OrderPurchaseDto;
import com.example.shopping_mall.dto.order.request.OrderUpdateDto;
import com.example.shopping_mall.dto.order.response.OrderDetailDto;
import com.example.shopping_mall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * 주문상세보기
     */
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<OrderDetailDto> detail(@PathVariable Long orderId,
                                                 @RequestParam Long accountId) {
        OrderDetailDto detail = orderService.detail(orderId, accountId);
        return ResponseEntity.ok().body(detail);
    }

    /**
     * 상품 구매 수정
     *
     * OrderType - PREPARING 만 수정이 가능함.
     */
    @PutMapping("/update")
    public void update(@RequestBody OrderUpdateDto orderUpdateDto) {
        orderService.update(orderUpdateDto);
    }

    /**
     * 상품 구매 취소
     *
     * OrderType - PREPARING 만 취소가 가능함.
     */
    @PutMapping("/cancel")
    public void cancel(@RequestBody OrderCancelDto orderCancelDto) {
        orderService.cancel(orderCancelDto);
    }


}
