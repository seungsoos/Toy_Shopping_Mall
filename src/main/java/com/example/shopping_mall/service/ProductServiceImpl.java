package com.example.shopping_mall.service;

import com.example.shopping_mall.common.FileComponent;
import com.example.shopping_mall.common.ResultCodeType;
import com.example.shopping_mall.common.exception.RootException;
import com.example.shopping_mall.dto.account.request.ProductSearchDto;
import com.example.shopping_mall.dto.product.response.ProductDetailDto;
import com.example.shopping_mall.dto.product.response.ProductListDto;
import com.example.shopping_mall.dto.product.request.ProductCreateDto;
import com.example.shopping_mall.dto.product.request.ProductDeleteDto;
import com.example.shopping_mall.dto.product.request.ProductUpdateDto;
import com.example.shopping_mall.entity.AccountEntity;
import com.example.shopping_mall.entity.ProductEntity;
import com.example.shopping_mall.repository.AccountRepository;
import com.example.shopping_mall.repository.ProductRepository;
import com.example.shopping_mall.repository.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final FileComponent fileComponent;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public void create(ProductCreateDto productCreateDto, MultipartFile multipartFile) {

        Long accountId = productCreateDto.getAccountId();
        AccountEntity accountEntity = accountRepository.findById(accountId)
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

    @Override
    public Page<ProductListDto> findByProductList(ProductSearchDto productListDto) {
        Pageable pageable = PageRequest.of(productListDto.getViewPage(), productListDto.getViewCount());
        return accountRepository.findAccountAndProductsByAccountId(productListDto, pageable);
    }

    @Override
    public ProductDetailDto detail(Long accountId, Long productId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));

        ProductEntity productEntity = productRepository.findByProductIdAndAccountEntity(productId, accountEntity)
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));

        return productMapper.INSTANCE.toProductDetailDto(productEntity, accountEntity);

    }

    private ProductEntity createProductEntity(ProductCreateDto productCreateDto, AccountEntity accountEntity, String uploadPath) {
        ProductEntity productEntity = ProductEntity.toEntity(productCreateDto, accountEntity, uploadPath);
        productEntity.settingAccountEntity(accountEntity);
        return productEntity;
    }
}
