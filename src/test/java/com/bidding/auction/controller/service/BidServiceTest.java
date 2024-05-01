package com.bidding.auction.controller.service;

import com.bidding.auction.dtos.BidDto;
import com.bidding.auction.exception.ProductNotFoundException;
import com.bidding.auction.models.Bid;
import com.bidding.auction.models.Products;
import com.bidding.auction.repository.BidRepository;
import com.bidding.auction.repository.ProductRepository;
import com.bidding.auction.service.BidService;
import com.bidding.auction.service.impl.BidServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BidServiceTest {

    private final BidRepository bidRepository;
    private final ProductRepository productRepository;
    private final BidService bidService;

    @Autowired
    public BidServiceTest(BidRepository bidRepository, ProductRepository productRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.bidService = new BidServiceImpl(bidRepository, productRepository);

    }

    @Test
    @WithMockUser(username = "testUser")
    public void testPlaceBid() {
        Products products = Products.builder().productName("test").category("mobile").minBid(new BigDecimal(1000)).build();
        productRepository.save(products);
        BidDto bidDto = BidDto.builder().amount(new BigDecimal(10000)).productId(1L).build();
        Bid bid = bidService.placeBid(bidDto);
        assertNotNull(bid.getBuyerName());
    }


    @Test
    @WithMockUser(username = "testUser")
    public void testPlaceBidNoProductExist() {

        BidDto bidDto = BidDto.builder().amount(new BigDecimal(100)).productId(1L).build();

        assertThrows(
                ProductNotFoundException.class,
                () -> bidService.placeBid(bidDto)
        );
    }


}
