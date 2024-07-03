package com.example.shopping_mall.service;

import com.example.shopping_mall.common.FileComponent;
import com.example.shopping_mall.common.ResultCodeType;
import com.example.shopping_mall.common.exception.RootException;
import com.example.shopping_mall.dto.product.request.ProductCreateDto;
import com.example.shopping_mall.dto.product.request.ProductDeleteDto;
import com.example.shopping_mall.dto.product.request.ProductUpdateDto;
import com.example.shopping_mall.entity.AccountEntity;
import com.example.shopping_mall.entity.ProductEntity;
import com.example.shopping_mall.repository.AccountRepository;
import com.example.shopping_mall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService{

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final FileComponent fileComponent;

    @Override
    @Transactional
    public void create(ProductCreateDto productCreateDto, MultipartFile multipartFile) {
        String loginId = productCreateDto.getLoginId();
        System.out.println("loginId = " + loginId);
        AccountEntity accountEntity = accountRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));

        ProductEntity productEntity;
        if (Objects.isNull(multipartFile)) {
            productEntity = createProductEntity(productCreateDto, accountEntity, null);
        } else {
            String upload = fileComponent.upload(multipartFile);
            productEntity = createProductEntity(productCreateDto, accountEntity, upload);
        }

        productRepository.save(productEntity);
    }

    @Override
    @Transactional
    public void update(ProductUpdateDto productUpdateDto, MultipartFile multipartFile) {

        Long productId = productUpdateDto.getProductId();
        System.out.println("productId = " + productId);
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));

        System.out.println("productEntity = " + productEntity);
        if (Objects.isNull(multipartFile)) {
            productEntity.update(productUpdateDto, null);
        } else {
            productEntity.update(productUpdateDto, fileComponent.upload(multipartFile));
        }

    }

    @Override
    public void delete(ProductDeleteDto productDeleteDto) {
        Long productId = productDeleteDto.getProductId();
        productRepository.deleteById(productId);
    }

    private ProductEntity createProductEntity(ProductCreateDto productCreateDto, AccountEntity accountEntity, String uploadPath) {
        ProductEntity productEntity = ProductEntity.toEntity(productCreateDto, accountEntity, uploadPath);
        productEntity.settingAccountEntity(accountEntity);
        return productEntity;
    }
}
