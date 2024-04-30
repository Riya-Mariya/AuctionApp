package com.bidding.auction.service;

import com.bidding.auction.dtos.ProductDto;
import com.bidding.auction.models.Products;

public interface ProductService {
    Products registerProducts(ProductDto productDto);
}
