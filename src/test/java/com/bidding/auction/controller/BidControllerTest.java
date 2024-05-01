package com.bidding.auction.controller;

import com.bidding.auction.dtos.BidDto;
import com.bidding.auction.models.Bid;
import com.bidding.auction.repository.BidRepository;
import com.bidding.auction.repository.ProductRepository;
import com.bidding.auction.service.AuctionService;
import com.bidding.auction.service.BidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebidding.shared.DataValidator.DataValidator;
import com.onlinebidding.shared.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(BidController.class)
public class BidControllerTest {
    @MockBean
    private BidService bidService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataValidator dataValidator;
    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @MockBean
    private BidRepository bidRepository;
    @MockBean
    private AuctionService auctionService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    @WithMockUser(authorities = "BUYER")
    public void testPlace_Bid_ReturnsOkResponse() throws Exception {
        BidDto bidDto = BidDto.builder()
                .productId(1L).amount(new BigDecimal(120000)).build();
        when(dataValidator.validateInputData(any())).thenReturn(null);
        when(bidService.placeBid(any())).thenReturn(new Bid());

        mockMvc.perform(MockMvcRequestBuilders.post("/auction/product/placeBid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bidDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(authorities = "SELLER")
    public void testPlace_Bid_ReturnsForBiddenResponse() throws Exception {
        BidDto bidDto = BidDto.builder()
                .productId(1L).amount(new BigDecimal(120000)).build();
        when(dataValidator.validateInputData(any())).thenReturn(null);
        when(bidService.placeBid(any())).thenReturn(new Bid());

        mockMvc.perform(MockMvcRequestBuilders.post("/auction/product/placeBid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bidDto)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "SELLER")
    public void testGet_Bid_ReturnsOkResponse() throws Exception {

        when(bidService.getBidDetails(any())).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/auction/product/1/getBid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
