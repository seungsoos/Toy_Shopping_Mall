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
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(source = "productEntity.productId", target = "productId"),
            @Mapping(source = "productEntity.brandName", target = "brandName"),
            @Mapping(source = "productEntity.name", target = "name"),
            @Mapping(source = "productEntity.productType", target = "productType"),
            @Mapping(source = "productEntity.quantity", target = "quantity"),
            @Mapping(source = "productEntity.information", target = "information"),
            @Mapping(source = "productEntity.imageUrl", target = "imageUrl")
    })
    ProductDetailDto toProductAndAdminDetailDto(ProductEntity productEntity, AccountEntity accountEntity);

    ProductDetailDto toProductDetailDto(ProductEntity productEntity);
}

