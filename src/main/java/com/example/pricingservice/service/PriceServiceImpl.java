package com.example.pricingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pricingservice.entity.Price;
import com.example.pricingservice.model.PriceResponse;
import com.example.pricingservice.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    private PriceRepository priceRepository;

	@Override
	public Optional<PriceResponse> getApplicablePrice(Integer brandId, Integer productId,
			LocalDateTime applicationDate) {
		Optional<Price> price = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
	                brandId, productId, applicationDate, applicationDate);
		 // Verificar si se encontró un precio
		if(price.isEmpty()) {
			return Optional.empty();
		}else {
			PriceResponse response = new PriceResponse();
			response.setProductId(price.get().getProductId());
			response.setBrandId(price.get().getBrandId());
			response.setPriceList(price.get().getPriceList());
			response.setStartDate(price.get().getStartDate());
			response.setEndDate(price.get().getEndDate());
			response.setFinalPrice(price.get().getPrice());
			
			return Optional.of(response);
		}
	}
}
