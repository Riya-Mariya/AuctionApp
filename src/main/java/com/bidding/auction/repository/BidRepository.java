package com.bidding.auction.repository;

import com.bidding.auction.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    Optional<Bid> findFirstByProductIdOrderByAmountDescBidTsAsc(Long productId);

}
