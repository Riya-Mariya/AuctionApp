package com.bidding.auction.controller;

import com.bidding.auction.dtos.BidDto;
import com.bidding.auction.models.Bid;
import com.bidding.auction.service.BidService;
import com.onlinebidding.shared.DataValidator.DataValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction/product")
@Slf4j
public class BidController {
    private static final Logger logger = LoggerFactory.getLogger(BidController.class);
    @Autowired
    private BidService bidService;
    @Autowired
    private DataValidator dataValidator;

    @PostMapping("/placeBid")
    @PreAuthorize("hasAuthority('BUYER')")
    public ResponseEntity<Object> placeBid(@RequestBody @Valid BidDto bidDto, BindingResult result) {
        if (result.hasErrors()) {
            var errorsMap = dataValidator.validateInputData(result);
            return ResponseEntity.badRequest().body(errorsMap);
        }
        Bid bid = bidService.placeBid(bidDto);
        return ResponseEntity.ok(bid);

    }

    @GetMapping("/{productId}/getBid")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<Object> getBidDetails(@PathVariable Long productId) {
        List<Bid> bid = bidService.getBidDetails(productId);
        return ResponseEntity.ok(bid);

    }

}
