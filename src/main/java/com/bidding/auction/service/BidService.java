package com.bidding.auction.service;

import com.bidding.auction.dtos.BidDto;
import com.bidding.auction.models.Bid;

public interface BidService {
    Bid placeBid(BidDto bidDto);
}
