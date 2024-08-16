package com.example.pricingservice.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.pricingservice.model.PriceResponse;

public interface PriceService {
	
    Optional<PriceResponse> getApplicablePrice(Integer brandId, Integer productId, LocalDateTime applicationDate);
}