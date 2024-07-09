package com.example.shopping_mall.repository;

import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.product.response.ProductListDto;
import com.example.shopping_mall.dto.common.QueryDslSearchCond;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.shopping_mall.entity.QAccountEntity.accountEntity;
import static com.example.shopping_mall.entity.QProductEntity.productEntity;
import static com.querydsl.core.group.GroupBy.groupBy;


@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Page<ProductListDto> findAccountAndProductsByAccountId(ProductSearchDto productListDto, Pageable pageable) {
        List<ProductListDto> dtoList = new ArrayList<>(queryFactory
                .from(accountEntity)
                .innerJoin(accountEntity.productEntityList, productEntity)
                .on(accountEntity.accountId.eq(productEntity.accountEntity.accountId))
                .where(
                        accountEntity.accountId.eq(productListDto.getAccountId()),
                        QueryDslSearchCond.searchBrandName(productListDto.getBrandName()),
                        QueryDslSearchCond.searchProductType(productListDto.getProductType()),
                        QueryDslSearchCond.searchName(productListDto.getName()),
                        QueryDslSearchCond.searchByDateRange(productListDto.getStartDtm(), productListDto.getEndDtm())
                )
                .transform(
                        groupBy(accountEntity.accountId).as(
                                Projections.constructor(ProductListDto.class,
                                        accountEntity.accountId,
                                        accountEntity.loginId,
                                        GroupBy.list(Projections.constructor(ProductListDto.ProductAccountDto.class,
                                                productEntity.productId,
                                                productEntity.brandName,
                                                productEntity.name,
                                                productEntity.productType,
                                                productEntity.quantity,
                                                productEntity.information))
                                )
                        )).values());

        Long countQuery = queryFactory.select(accountEntity.count())
                .from(accountEntity)
                .innerJoin(accountEntity.productEntityList, productEntity)
                .on(accountEntity.accountId.eq(productEntity.accountEntity.accountId))
                .where(
                        accountEntity.accountId.eq(productListDto.getAccountId()),
                        QueryDslSearchCond.searchBrandName(productListDto.getBrandName()),
                        QueryDslSearchCond.searchProductType(productListDto.getProductType()),
                        QueryDslSearchCond.searchName(productListDto.getName()),
                        QueryDslSearchCond.searchByDateRange(productListDto.getStartDtm(), productListDto.getEndDtm())
                )
                .fetchOne();

        return PageableExecutionUtils.getPage(dtoList, pageable,() -> countQuery);
    }



}
