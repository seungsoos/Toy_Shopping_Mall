package com.example.shopping_mall.controller;

import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.order.request.OrderCancelDto;
import com.example.shopping_mall.dto.order.request.OrderPurchaseDto;
import com.example.shopping_mall.dto.order.request.OrderUpdateDto;
import com.example.shopping_mall.dto.order.response.OrderDetailDto;
import com.example.shopping_mall.dto.product.response.ProductListByAdminAccountDto;
import com.example.shopping_mall.dto.product.response.ProductListDto;
import com.example.shopping_mall.entity.enums.BrandName;
import com.example.shopping_mall.entity.enums.ProductType;
import com.example.shopping_mall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 상품 조회 / 검색
     */
    @GetMapping("/list")
    public ResponseEntity<Page<ProductListDto>> findByProductList(@RequestParam(required = false) BrandName brandName,
                                                                                @RequestParam(required = false) String name,
                                                                                @RequestParam(required = false) ProductType productType,
                                                                                @RequestParam(required = false, value = "startDtm") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDtm,
                                                                                @RequestParam(required = false, value = "endDtm") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDtm,
                                                                                @RequestParam(value = "viewPage", defaultValue = "0") Integer viewPage,
                                                                                @RequestParam(value = "viewCount", defaultValue = "20") Integer viewCount
    ) {
        ProductSearchDto productSearchDto = getProductSearchDto(brandName, name, productType, startDtm, endDtm, viewPage, viewCount);
        Page<ProductListDto> productDtoList = orderService.list(productSearchDto);
        return ResponseEntity.ok().body(productDtoList);
    }

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

    private ProductSearchDto getProductSearchDto(BrandName brandName, String name, ProductType productType, LocalDate startDtm, LocalDate endDtm, Integer viewPage, Integer viewCount) {
        return ProductSearchDto.builder()
                .brandName(brandName)
                .name(name)
                .productType(productType)
                .startDtm(startDtm)
                .endDtm(endDtm)
                .viewPage(viewPage)
                .viewCount(viewCount)
                .build();
    }
}
