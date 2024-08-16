package com.example.pricingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.pricingservice.entity.Price;
import com.example.pricingservice.repository.PriceRepository;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public void run(String... args) throws Exception {
        priceRepository.save(new Price(1, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1, 35455, 0, 35.50, "EUR"));
        priceRepository.save(new Price(1, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 6, 14, 18, 30), 2, 35455, 1, 25.45, "EUR"));
        priceRepository.save(new Price(1, LocalDateTime.of(2020, 6, 15, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0), 3, 35455, 1, 30.50, "EUR"));
        priceRepository.save(new Price(1, LocalDateTime.of(2020, 6, 15, 16, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4, 35455, 1, 38.95, "EUR"));
    }
}