package com.bidding.auction.service;

import com.bidding.auction.models.Bid;

public interface AuctionService {
    Bid endAuction(Long productId);
}
