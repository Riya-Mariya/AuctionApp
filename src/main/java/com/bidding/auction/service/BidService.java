package com.bidding.auction.service;

import com.bidding.auction.dtos.BidDto;
import com.bidding.auction.models.Bid;

import java.util.List;

public interface BidService {
    Bid placeBid(BidDto bidDto);

    List<Bid> getBidDetails(Long productId);
}
