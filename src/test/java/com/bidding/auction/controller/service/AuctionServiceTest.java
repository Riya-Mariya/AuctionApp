package com.bidding.auction.controller.service;

import com.bidding.auction.models.Bid;
import com.bidding.auction.models.Products;
import com.bidding.auction.repository.BidRepository;
import com.bidding.auction.repository.ProductRepository;
import com.bidding.auction.service.AuctionService;
import com.bidding.auction.service.impl.AuctionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class AuctionServiceTest {
    private final BidRepository bidRepository;
    private final ProductRepository productRepository;
    private final AuctionService auctionService;

    @Autowired
    public AuctionServiceTest(BidRepository bidRepository, ProductRepository productRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.auctionService = new AuctionServiceImpl(bidRepository, productRepository);

    }

    @Test
    public void testEndAuction() {
        Products products = Products.builder().productId(1L).productName("test").category("mobile").minBid(new BigDecimal(1000)).build();
        productRepository.save(products);
        Bid bid = Bid.builder().productId(1L).amount(new BigDecimal(20000)).build();
        bidRepository.save(bid);
        Bid bid1 = auctionService.endAuction(1L);
        assertNotNull(bid1);
    }


}
