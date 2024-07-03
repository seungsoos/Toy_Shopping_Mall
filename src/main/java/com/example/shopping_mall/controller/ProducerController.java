package com.example.shopping_mall.controller;

import com.example.shopping_mall.dto.product.ProductCreateDto;
import com.example.shopping_mall.dto.product.ProductUpdateDto;
import com.example.shopping_mall.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/create")
    public void create(@RequestPart ProductCreateDto productCreateDto,
                       @RequestPart(name = "image_file",required = false) MultipartFile multipartFile) {
        producerService.create(productCreateDto, multipartFile);
    }


    @PutMapping("/update")
    public void update(@RequestPart ProductUpdateDto productUpdateDto,
                       @RequestPart(name = "image_file",required = false) MultipartFile multipartFile) {
        producerService.update(productUpdateDto, multipartFile);
    }


}
