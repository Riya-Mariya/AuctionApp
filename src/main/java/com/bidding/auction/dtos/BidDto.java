package com.bidding.auction.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BidDto {
    @NotEmpty
    private BigDecimal amount;
    @NotEmpty
    private Long productId;
}

