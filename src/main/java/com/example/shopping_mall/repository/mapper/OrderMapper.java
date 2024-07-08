package com.example.shopping_mall.repository.mapper;

import com.example.shopping_mall.dto.order.response.OrderDetailDto;
import com.example.shopping_mall.dto.product.response.ProductDetailDto;
import com.example.shopping_mall.entity.AccountEntity;
import com.example.shopping_mall.entity.OrderEntity;
import com.example.shopping_mall.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    OrderDetailDto toOrderDetailDto(OrderEntity orderEntity);
}

