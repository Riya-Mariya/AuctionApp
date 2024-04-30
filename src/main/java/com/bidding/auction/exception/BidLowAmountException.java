package com.bidding.auction.exception;

public class BidLowAmountException extends RuntimeException{
    public BidLowAmountException(String message) {
        super(message);
    }

}
