package com.example.shopping_mall.controller;

import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.product.response.ProductDetailDto;
import com.example.shopping_mall.dto.product.response.ProductListByAdminAccountDto;
import com.example.shopping_mall.dto.product.request.ProductCreateDto;
import com.example.shopping_mall.dto.product.request.ProductDeleteDto;
import com.example.shopping_mall.dto.product.request.ProductUpdateDto;
import com.example.shopping_mall.entity.enums.BrandName;
import com.example.shopping_mall.entity.enums.ProductType;
import com.example.shopping_mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public void create(@RequestPart(name = "productCreateDto") ProductCreateDto productCreateDto,
                       @RequestPart(name = "image_file",required = false) MultipartFile multipartFile) {
        productService.create(productCreateDto, multipartFile);
    }


    @PutMapping("/update")
    public void update(@RequestPart(name = "productUpdateDto") ProductUpdateDto productUpdateDto,
                       @RequestPart(name = "image_file",required = false) MultipartFile multipartFile) {
        productService.update(productUpdateDto, multipartFile);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody ProductDeleteDto productDeleteDto) {
        productService.delete(productDeleteDto);
    }

    @GetMapping("list")
    public ResponseEntity<Page<ProductListByAdminAccountDto>> findByProductList(@RequestParam Long accountId,
                                                                                @RequestParam(required = false) BrandName brandName,
                                                                                @RequestParam(required = false) String name,
                                                                                @RequestParam(required = false) ProductType productType,
                                                                                @RequestParam(required = false, value = "startDtm") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDtm,
                                                                                @RequestParam(required = false, value = "endDtm") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDtm,
                                                                                @RequestParam(value = "viewPage", defaultValue = "0") Integer viewPage,
                                                                                @RequestParam(value = "viewCount", defaultValue = "20") Integer viewCount
    ) {
        ProductSearchDto productSearchDto = getProductSearchDto(accountId, brandName, name, productType, startDtm, endDtm, viewPage, viewCount);
        Page<ProductListByAdminAccountDto> byProductList = productService.findByProductList(productSearchDto);
        return ResponseEntity.ok().body(byProductList);
    }

    @GetMapping("/detail/{productId}")
    public ResponseEntity<ProductDetailDto> detail(@RequestParam Long accountId,
                                                   @PathVariable Long productId) {
        ProductDetailDto productDetailDto = productService.detail(accountId, productId);
        return ResponseEntity.ok().body(productDetailDto);
    }

    private ProductSearchDto getProductSearchDto(Long accountId, BrandName brandName, String name, ProductType productType, LocalDate startDtm, LocalDate endDtm, Integer viewPage, Integer viewCount) {
        return ProductSearchDto.builder()
                .accountId(accountId)
                .brandName(brandName)
                .name(name)
                .productType(productType)
                .startDtm(startDtm)
                .endDtm(endDtm)
                .viewPage(viewPage)
                .viewCount(viewCount)
                .build();
    }
}
