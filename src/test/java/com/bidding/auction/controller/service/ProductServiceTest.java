package com.bidding.auction.controller.service;

import com.bidding.auction.dtos.ProductDto;
import com.bidding.auction.models.Products;
import com.bidding.auction.repository.ProductRepository;
import com.bidding.auction.service.ProductService;
import com.bidding.auction.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private final ProductRepository productRepository;

    private final ProductService productService;

    @Autowired
    public ProductServiceTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productService = new ProductServiceImpl(productRepository);

    }

    @Test
    @WithMockUser(username = "testUser")
    public void testRegisterProductInfo_ValidUser_ReturnsProductInfo() {
        ProductDto productDto = ProductDto.builder().productName("test").category("mobile").minBid(new BigDecimal(1000)).build();
        Products products = productService.registerProducts(productDto);
        assertNotNull(products.getProductId());
        assertEquals(productDto.getProductName(), products.getProductName());
    }


}
