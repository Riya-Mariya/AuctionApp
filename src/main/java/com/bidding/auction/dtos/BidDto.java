package com.bidding.auction.dtos;

import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Amount cannot be empty")
    private BigDecimal amount;
    @NotNull(message = "Product Id cannot be empty")
    private Long productId;
}

