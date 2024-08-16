package com.example.pricingservice.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pricingservice.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	
    public Optional<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer brandId, 
												    												Integer productId, 
												    												LocalDateTime date, 
												    												LocalDateTime date2);
    
}