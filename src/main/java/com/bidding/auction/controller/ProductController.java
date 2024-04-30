package com.bidding.auction.controller;

import com.bidding.auction.dtos.ProductDto;
import com.bidding.auction.models.Products;
import com.bidding.auction.service.ProductService;
import com.onlinebidding.shared.DataValidator.DataValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auction/product")
@Slf4j
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private DataValidator dataValidator;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<Object> register(@RequestBody @Valid ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            var errorsMap = dataValidator.validateInputData(result);
            return ResponseEntity.badRequest().body(errorsMap);
        }
        try {
            Products products = productService.registerProducts(productDto);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("Exception while adding the products {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error while inserting product details");
        }


    }

}
