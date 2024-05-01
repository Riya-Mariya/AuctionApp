package com.bidding.auction.controller;

import com.bidding.auction.dtos.ProductDto;
import com.bidding.auction.models.Products;
import com.bidding.auction.repository.BidRepository;
import com.bidding.auction.repository.ProductRepository;
import com.bidding.auction.service.AuctionService;
import com.bidding.auction.service.ProductService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @MockBean
    private ProductService productService;
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
    @WithMockUser(authorities = "SELLER")
    public void testRegister_Product_ReturnsOkResponse() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .productName("Iphone").category("Mobile").minBid(new BigDecimal(100000)).build();
        when(dataValidator.validateInputData(any())).thenReturn(null);
        when(productService.registerProducts(any())).thenReturn(new Products());

        mockMvc.perform(MockMvcRequestBuilders.post("/auction/product/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(authorities = "BUYER")
    public void testRegister_Product_ReturnsUnAuthorizedResponse() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .productName("Iphone").category("Mobile").minBid(new BigDecimal(100000)).build();
        when(dataValidator.validateInputData(any())).thenReturn(null);
        when(productService.registerProducts(any())).thenReturn(new Products());

        mockMvc.perform(MockMvcRequestBuilders.post("/auction/product/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "SELLER")
    public void testRegister_Product_ReturnsBadResponse() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .productName("Iphone").category("Mobile").build();
        when(dataValidator.validateInputData(any())).thenReturn(null);
        when(productService.registerProducts(any())).thenReturn(new Products());

        mockMvc.perform(MockMvcRequestBuilders.post("/auction/product/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
