package com.example.shopping_mall.service;

import com.example.shopping_mall.dto.product.request.ProductCreateDto;
import com.example.shopping_mall.dto.product.request.ProductDeleteDto;
import com.example.shopping_mall.dto.product.request.ProductUpdateDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProducerService {

    void create(ProductCreateDto productCreateDto, MultipartFile multipartFile);

    void update(ProductUpdateDto productUpdateDto, MultipartFile multipartFile);

    void delete(ProductDeleteDto productDeleteDto);
}
