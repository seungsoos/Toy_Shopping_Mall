package com.example.shopping_mall.service;

import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.product.response.ProductDetailDto;
import com.example.shopping_mall.dto.product.response.ProductListByAdminAccountDto;
import com.example.shopping_mall.dto.product.request.ProductCreateDto;
import com.example.shopping_mall.dto.product.request.ProductDeleteDto;
import com.example.shopping_mall.dto.product.request.ProductUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    void create(ProductCreateDto productCreateDto, MultipartFile multipartFile);

    void update(ProductUpdateDto productUpdateDto, MultipartFile multipartFile);

    void delete(ProductDeleteDto productDeleteDto);

    Page<ProductListByAdminAccountDto> findByProductList(ProductSearchDto productListDto);

    ProductDetailDto detail(Long accountId, Long productId);
}
