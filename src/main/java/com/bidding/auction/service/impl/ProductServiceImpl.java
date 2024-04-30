package com.bidding.auction.service.impl;

import com.bidding.auction.dtos.ProductDto;
import com.bidding.auction.enums.ProductStatus;
import com.bidding.auction.models.Products;
import com.bidding.auction.repository.ProductRepository;
import com.bidding.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Products registerProducts(ProductDto productDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Products products = Products.builder()
                .productName(productDto.getProductName())
                .description(productDto.getDescription())
                .category(productDto.getCategory())
                .status(ProductStatus.AVAILABLE.name())
                .minBid(productDto.getMinBid())
                .createdDate(LocalDate.now())
                .sellerName(authentication.getName())
                .build();
        return productRepository.save(products);
    }
}
