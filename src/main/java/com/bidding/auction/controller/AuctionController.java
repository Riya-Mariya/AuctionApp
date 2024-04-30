package com.bidding.auction.controller;

import com.bidding.auction.models.Bid;
import com.bidding.auction.service.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auction/product")
@Slf4j
public class AuctionController {
    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);
    @Autowired
    private AuctionService auctionService;

    @PreAuthorize("hasAuthority('SELLER')")
    @PutMapping("/{productId}/endAuction")
    public ResponseEntity<Object> endAuction(@PathVariable Long productId) {
        try {
            Bid endedProduct = auctionService.endAuction(productId);
            return ResponseEntity.ok(endedProduct);
        } catch (Exception e) {
            logger.error("Exception endAuction {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error while finding the winner");
        }
    }
}
