package com.example.shopping_mall.batch;

import com.example.shopping_mall.entity.OrderEntity;
import com.example.shopping_mall.entity.enums.OrderType;
import com.example.shopping_mall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class orderStatusComponent {

    private final OrderRepository orderRepository;

    /**
     * 5분주기로 주문의 상태를 변경한다.
     *
     * PREPARING -> SHIPPED
     */
    @Scheduled(cron = "0 */5 * * * *")
    @Transactional
    public void updateOrderTypeToShipping() {
        List<OrderEntity> updateTargetOrderEntity = orderRepository.findByOrderType(OrderType.PREPARING);
        log.info(" > orderType change to SHIPPED start = {}", updateTargetOrderEntity);

        updateTargetOrderEntity.forEach(orderEntity -> orderEntity.updateOrderType(OrderType.SHIPPING));
        log.info(" > orderType change to SHIPPED end = {}", updateTargetOrderEntity);
    }


    /**
     * 10분주기로 주문의 상태를 변경한다.
     *
     * SHIPPED -> DELIVERED
     */
    @Scheduled(cron = "0 */10 * * * *")
    @Transactional
    public void updateOrderTypeToDelivered() {
        List<OrderEntity> updateTargetOrderEntity = orderRepository.findByOrderType(OrderType.SHIPPING);
        log.info(" > orderType change to DELIVERED start = {}", updateTargetOrderEntity);

        updateTargetOrderEntity.forEach(orderEntity -> orderEntity.updateOrderType(OrderType.DELIVERED));
        log.info(" > orderType change to DELIVERED end = {}", updateTargetOrderEntity);
    }



}
