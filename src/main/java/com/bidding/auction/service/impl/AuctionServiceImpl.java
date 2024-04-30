package com.bidding.auction.service.impl;

import com.bidding.auction.enums.ProductStatus;
import com.bidding.auction.exception.BidNotFoundException;
import com.bidding.auction.exception.ProductNotFoundException;
import com.bidding.auction.models.Bid;
import com.bidding.auction.models.Products;
import com.bidding.auction.repository.BidRepository;
import com.bidding.auction.repository.ProductRepository;
import com.bidding.auction.service.AuctionService;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService {
    private final BidRepository bidRepository;
    private final ProductRepository productRepository;

    public AuctionServiceImpl(BidRepository bidRepository, ProductRepository productRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Bid endAuction(Long productId) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        Bid winningBid = bidRepository.findFirstByProductIdOrderByAmountDescBidTsAsc(productId)
                .orElseThrow(() -> new BidNotFoundException("No bids found for the product with id: " + productId));

        product.setStatus(ProductStatus.SOLD);
        product.setWinningBidBuyer(winningBid.getBuyerName());
        productRepository.save(product);

        return winningBid;
    }
}
