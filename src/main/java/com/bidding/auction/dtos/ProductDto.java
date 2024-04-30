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
public class ProductDto {
    @NotEmpty
    private String productName;
    private String description;
    @NotEmpty
    private String category;
    @NotEmpty
    private BigDecimal minBid;
}
