package com.example.shopping_mall.service;

import com.example.shopping_mall.common.ResultCodeType;
import com.example.shopping_mall.common.exception.RootException;
import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.order.request.OrderCancelDto;
import com.example.shopping_mall.dto.order.request.OrderPurchaseDto;
import com.example.shopping_mall.dto.order.request.OrderUpdateDto;
import com.example.shopping_mall.dto.order.response.OrderDetailDto;
import com.example.shopping_mall.dto.product.response.ProductListByAdminAccountDto;
import com.example.shopping_mall.dto.product.response.ProductListDto;
import com.example.shopping_mall.entity.AccountEntity;
import com.example.shopping_mall.entity.OrderEntity;
import com.example.shopping_mall.entity.ProductEntity;
import com.example.shopping_mall.entity.ProductOrderEntity;
import com.example.shopping_mall.entity.enums.OrderType;
import com.example.shopping_mall.repository.AccountRepository;
import com.example.shopping_mall.repository.OrderRepository;
import com.example.shopping_mall.repository.ProductRepository;
import com.example.shopping_mall.repository.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public void purchase(OrderPurchaseDto orderPurchaseDto) {

        ProductEntity productEntity = getProductEntity(orderPurchaseDto);
        AccountEntity accountEntity = getAccountEntity(orderPurchaseDto);

        validateRequestQuantity(orderPurchaseDto, productEntity);

        OrderEntity orderEntity = builderOrderEntity(orderPurchaseDto, accountEntity);
        orderEntity.settingAccountEntity(accountEntity);
        orderRepository.save(orderEntity);

        ProductOrderEntity productOrderEntity = getProductOrderEntity(productEntity, orderEntity);
        orderEntity.settingProductOrderEntity(productOrderEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetailDto detail(Long orderId, Long accountId) {
        OrderEntity orderEntity = getOrderEntity(orderId);
        validateAccountId(accountId, orderEntity);

        return orderMapper.INSTANCE.toOrderDetailDto(orderEntity);
    }

    @Override
    @Transactional
    public void update(OrderUpdateDto orderUpdateDto) {
        OrderEntity orderEntity = orderRepository.findById(orderUpdateDto.getOrderId())
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));
        isNotPreparing(orderEntity);
        orderEntity.updateQuantity(orderUpdateDto.getQuantity());
    }

    @Override
    @Transactional
    public void cancel(OrderCancelDto orderCancelDto) {
        OrderEntity orderEntity = orderRepository.findById(orderCancelDto.getOrderId())
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));

        isNotPreparing(orderEntity);
        orderEntity.updateOrderType(OrderType.CANCEL);
    }

    private void isNotPreparing(OrderEntity orderEntity) {
        if (orderEntity.isNotPreparing()) {
            throw new RootException(ResultCodeType.SERVER_ERROR_ORDER_TYPE_IS_NOT_PREPARING);
        }
    }

    private OrderEntity getOrderEntity(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));
    }


    private void validateAccountId(Long accountId, OrderEntity orderEntity) {
        if (!Objects.equals(orderEntity.getAccountEntity().getAccountId(), accountId)) {
            throw new RootException(ResultCodeType.SERVER_ERROR_4S000000);
        }
    }

    private OrderEntity builderOrderEntity(OrderPurchaseDto orderPurchaseDto, AccountEntity accountEntity) {
        return OrderEntity.builder()
                .quantity(orderPurchaseDto.getQuantity())
                .orderType(OrderType.PREPARING)
                .accountEntity(accountEntity)
                .build();
    }

    private AccountEntity getAccountEntity(OrderPurchaseDto orderPurchaseDto) {
        return accountRepository.findById(orderPurchaseDto.getAccountId())
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));
    }

    private ProductEntity getProductEntity(OrderPurchaseDto orderPurchaseDto) {
        return productRepository.findById(orderPurchaseDto.getProductId())
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));
    }

    private ProductOrderEntity getProductOrderEntity(ProductEntity productEntity, OrderEntity orderEntity) {
        return ProductOrderEntity
                .builder()
                .productEntity(productEntity)
                .orderEntity(orderEntity)
                .build();
    }

    private void validateRequestQuantity(OrderPurchaseDto orderPurchaseDto, ProductEntity productEntity) {
        if (productEntity.requestQuantityIsLarger(orderPurchaseDto.getQuantity())) {
            throw new RootException(ResultCodeType.SERVER_ERROR_REQUEST_QUANTITY);
        }
    }
}
