package com.example.shopping_mall.repository;

import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.product.response.ProductListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<ProductListDto> findAccountAndProductsByAccountId(ProductSearchDto productListDto, Pageable pageable);

}
