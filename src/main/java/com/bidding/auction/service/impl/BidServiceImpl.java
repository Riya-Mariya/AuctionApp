package com.bidding.auction.service.impl;

import com.bidding.auction.dtos.BidDto;
import com.bidding.auction.exception.BidLowAmountException;
import com.bidding.auction.exception.ProductNotFoundException;
import com.bidding.auction.models.Bid;
import com.bidding.auction.models.Products;
import com.bidding.auction.repository.BidRepository;
import com.bidding.auction.repository.ProductRepository;
import com.bidding.auction.service.BidService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final ProductRepository productRepository;

    public BidServiceImpl(BidRepository bidRepository, ProductRepository productRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Bid placeBid(BidDto bidDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Products product = productRepository.findById(bidDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + bidDto.getProductId()));

        if (bidDto.getAmount().compareTo(product.getMinBid()) <= 0) {
            throw new BidLowAmountException("Bid amount is below the minimum bid");
        }

        Bid bid = Bid.builder()
                .productId(bidDto.getProductId())
                .amount(bidDto.getAmount())
                .buyerName(authentication.getName())
                .bidTs(LocalDateTime.now())
                .build();
        return bidRepository.save(bid);
    }
}
