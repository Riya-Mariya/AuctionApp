package com.bidding.auction.controller;

import com.bidding.auction.models.Bid;
import com.bidding.auction.repository.BidRepository;
import com.bidding.auction.repository.ProductRepository;
import com.bidding.auction.service.AuctionService;
import com.bidding.auction.service.BidService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AuctionController.class)

public class AuctionControllerTest {

    @MockBean
    private BidService bidService;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @MockBean
    private BidRepository bidRepository;
    @MockBean
    private AuctionService auctionService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    @WithMockUser(authorities = "SELLER")
    public void testPlace_Bid_ReturnsOkResponse() throws Exception {

        when(auctionService.endAuction(any())).thenReturn(new Bid());

        mockMvc.perform(MockMvcRequestBuilders.put("/auction/product/1/endAuction")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
