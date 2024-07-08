package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.OrderEntity;
import com.example.shopping_mall.entity.enums.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByOrderType(OrderType orderType);
}
