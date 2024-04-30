package com.bidding.auction.dtos;

import jakarta.validation.constraints.NotEmpty;
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
public class ProductDto {
    @NotEmpty(message = "Product name cannot be empty")
    private String productName;
    private String description;
    @NotEmpty(message = "Category cannot be empty")
    private String category;
    @NotNull(message = "Minimum bid amount cannot be empty")
    private BigDecimal minBid;
}
