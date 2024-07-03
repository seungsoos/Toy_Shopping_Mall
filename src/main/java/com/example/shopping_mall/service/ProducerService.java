package com.example.shopping_mall.service;

import com.example.shopping_mall.dto.product.ProductCreateDto;
import com.example.shopping_mall.dto.product.ProductUpdateDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProducerService {

    void create(ProductCreateDto productCreateDto, MultipartFile multipartFile);

    void update(ProductUpdateDto productUpdateDto, MultipartFile multipartFile);
}
