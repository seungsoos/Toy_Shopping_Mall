package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.AccountEntity;
import com.example.shopping_mall.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductIdAndAccountEntity(Long productId, AccountEntity accountEntity);
}
