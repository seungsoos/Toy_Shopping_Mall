package com.example.shopping_mall.repository;

import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.product.response.ProductListByAdminAccountDto;
import com.example.shopping_mall.dto.product.response.ProductListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<ProductListByAdminAccountDto> findAccountAndProductsByAccountId(ProductSearchDto productSearchDto, Pageable pageable);
    Page<ProductListDto> findByProductSearch(ProductSearchDto productListDto, Pageable pageable);

}
