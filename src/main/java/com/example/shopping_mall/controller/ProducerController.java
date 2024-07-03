package com.example.shopping_mall.controller;

import com.example.shopping_mall.dto.product.request.ProductCreateDto;
import com.example.shopping_mall.dto.product.request.ProductDeleteDto;
import com.example.shopping_mall.dto.product.request.ProductUpdateDto;
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
    public void create(@RequestPart(name = "producerCreateDto") ProductCreateDto productCreateDto,
                       @RequestPart(name = "image_file",required = false) MultipartFile multipartFile) {
        producerService.create(productCreateDto, multipartFile);
    }


    @PutMapping("/update")
    public void update(@RequestPart(name = "productUpdateDto") ProductUpdateDto productUpdateDto,
                       @RequestPart(name = "image_file",required = false) MultipartFile multipartFile) {
        producerService.update(productUpdateDto, multipartFile);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody ProductDeleteDto productDeleteDto) {
        producerService.delete(productDeleteDto);
    }

}
