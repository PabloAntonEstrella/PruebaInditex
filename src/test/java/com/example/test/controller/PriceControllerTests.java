package com.example.test.controller;

import com.example.pricingservice.FeignClientApplication;
import com.example.pricingservice.controller.PriceController;
import com.example.pricingservice.entity.Price;
import com.example.pricingservice.model.PriceRequest;
import com.example.pricingservice.model.PriceResponse;
import com.example.pricingservice.service.PriceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(classes = FeignClientApplication.class)
@AutoConfigureMockMvc
public class PriceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PriceServiceImpl priceService;
    
    @InjectMocks
    private PriceController priceController;

    @Test
    void testGetPrice_WhenPriceIsFound() throws Exception {
        PriceRequest request = createPriceRequest(1, 35455, LocalDateTime.of(2020, 6, 14, 10, 0));
        PriceResponse response = new PriceResponse();
        response.setProductId(35455);
        response.setBrandId(1);
        response.setPriceList(1);
        response.setStartDate(LocalDateTime.of(2020, 6, 14, 8, 0));
        response.setEndDate(LocalDateTime.of(2020, 6, 14, 12, 0));
        response.setFinalPrice(25.45);

        when(priceService.getApplicablePrice(request.getBrandId(), request.getProductId(), request.getApplicationDate()))
                .thenReturn(Optional.of(response));

        mockMvc.perform(post("/v1/inditex/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPrice_WhenPriceIsNotFound() throws Exception {
        PriceRequest request = createPriceRequest(1, 35455, LocalDateTime.of(2020, 6, 14, 21, 0));

        when(priceService.getApplicablePrice(request.getBrandId(), request.getProductId(), request.getApplicationDate()))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/v1/inditex/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetPrice_WhenExceptionIsThrown() throws Exception {
        PriceRequest request = createPriceRequest(1, 35455, LocalDateTime.of(2020, 6, 14, 10, 0));

        when(priceService.getApplicablePrice(request.getBrandId(), request.getProductId(), request.getApplicationDate()))
                .thenThrow(new RuntimeException("Database error"));

        mockMvc.perform(post("/v1/inditex/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testGetPrice_10AM_14thJune() throws Exception {
        testGetPrice(LocalDateTime.of(2020, 6, 14, 10, 0), 25.45);
    }

    @Test
    void testGetPrice_4PM_14thJune() throws Exception {
        testGetPrice(LocalDateTime.of(2020, 6, 14, 16, 0), 35.50);
    }

    @Test
    void testGetPrice_9PM_14thJune() throws Exception {
        testGetPrice(LocalDateTime.of(2020, 6, 14, 21, 0), null);
    }

    @Test
    void testGetPrice_10AM_15thJune() throws Exception {
        testGetPrice(LocalDateTime.of(2020, 6, 15, 10, 0), 30.50);
    }

    @Test
    void testGetPrice_9PM_16thJune() throws Exception {
        testGetPrice(LocalDateTime.of(2020, 6, 16, 21, 0), null);
    }

    private void testGetPrice(LocalDateTime applicationDate, Double expectedPrice) throws Exception {
        PriceRequest request = createPriceRequest(1, 35455, applicationDate);
        PriceResponse response = new PriceResponse();
        response.setProductId(35455);
        response.setBrandId(1);
        response.setPriceList(1);
        response.setStartDate(applicationDate.minusHours(2));
        response.setEndDate(applicationDate.plusHours(2));
        response.setFinalPrice(expectedPrice);

        if (expectedPrice != null) {
            when(priceService.getApplicablePrice(request.getBrandId(), request.getProductId(), request.getApplicationDate()))
                    .thenReturn(Optional.of(response));

            mockMvc.perform(post("/v1/inditex/price")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk());
        } else {
            when(priceService.getApplicablePrice(request.getBrandId(), request.getProductId(), request.getApplicationDate()))
                    .thenReturn(Optional.empty());

            mockMvc.perform(post("/v1/inditex/price")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isNoContent());
        }
    }

    private PriceRequest createPriceRequest(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        PriceRequest request = new PriceRequest();
        request.setBrandId(brandId);
        request.setProductId(productId);
        request.setApplicationDate(applicationDate);
        return request;
    }
}
