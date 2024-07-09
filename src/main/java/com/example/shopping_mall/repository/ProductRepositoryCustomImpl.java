package com.example.shopping_mall.repository;

import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.product.response.ProductListByAdminAccountDto;
import com.example.shopping_mall.dto.common.QueryDslSearchCond;
import com.example.shopping_mall.dto.product.response.ProductListDto;
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

    public Page<ProductListByAdminAccountDto> findAccountAndProductsByAccountId(ProductSearchDto productSearchDto, Pageable pageable) {
        List<ProductListByAdminAccountDto> content = new ArrayList<>(queryFactory
                .from(accountEntity)
                .innerJoin(accountEntity.productEntityList, productEntity)
                .on(accountEntity.accountId.eq(productEntity.accountEntity.accountId))
                .where(
                        accountEntity.accountId.eq(productSearchDto.getAccountId()),
                        QueryDslSearchCond.searchBrandName(productSearchDto.getBrandName()),
                        QueryDslSearchCond.searchProductType(productSearchDto.getProductType()),
                        QueryDslSearchCond.searchName(productSearchDto.getName()),
                        QueryDslSearchCond.searchByDateRange(productSearchDto.getStartDtm(), productSearchDto.getEndDtm())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .transform(
                        groupBy(accountEntity.accountId).as(
                                Projections.constructor(ProductListByAdminAccountDto.class,
                                        accountEntity.accountId,
                                        accountEntity.loginId,
                                        GroupBy.list(Projections.constructor(ProductListByAdminAccountDto.ProductAccountDto.class,
                                                productEntity.productId,
                                                productEntity.brandName,
                                                productEntity.name,
                                                productEntity.productType,
                                                productEntity.quantity,
                                                productEntity.information))
                                )
                        ))
                .values());

        Long countQuery = queryFactory.select(accountEntity.count())
                .from(accountEntity)
                .innerJoin(accountEntity.productEntityList, productEntity)
                .on(accountEntity.accountId.eq(productEntity.accountEntity.accountId))
                .where(
                        accountEntity.accountId.eq(productSearchDto.getAccountId()),
                        QueryDslSearchCond.searchBrandName(productSearchDto.getBrandName()),
                        QueryDslSearchCond.searchProductType(productSearchDto.getProductType()),
                        QueryDslSearchCond.searchName(productSearchDto.getName()),
                        QueryDslSearchCond.searchByDateRange(productSearchDto.getStartDtm(), productSearchDto.getEndDtm())
                )

                .fetchOne();

        return PageableExecutionUtils.getPage(content, pageable,() -> countQuery);
    }

    @Override
    public Page<ProductListDto> findByProductSearch(ProductSearchDto productSearchDto, Pageable pageable) {

        List<ProductListDto> content = queryFactory
                .select(Projections.constructor(ProductListDto.class,
                        productEntity.productId,
                        productEntity.brandName,
                        productEntity.name,
                        productEntity.productType,
                        productEntity.quantity,
                        productEntity.information
                        )
                )
                .from(productEntity)
                .where(
                        QueryDslSearchCond.searchBrandName(productSearchDto.getBrandName()),
                        QueryDslSearchCond.searchProductType(productSearchDto.getProductType()),
                        QueryDslSearchCond.searchName(productSearchDto.getName()),
                        QueryDslSearchCond.searchByDateRange(productSearchDto.getStartDtm(), productSearchDto.getEndDtm())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long countQuery = queryFactory.select(productEntity.count())
                .from(productEntity)
                .where(
                        QueryDslSearchCond.searchBrandName(productSearchDto.getBrandName()),
                        QueryDslSearchCond.searchProductType(productSearchDto.getProductType()),
                        QueryDslSearchCond.searchName(productSearchDto.getName()),
                        QueryDslSearchCond.searchByDateRange(productSearchDto.getStartDtm(), productSearchDto.getEndDtm())
                )
                .fetchOne();

        return PageableExecutionUtils.getPage(content, pageable,() -> countQuery);
    }


}
