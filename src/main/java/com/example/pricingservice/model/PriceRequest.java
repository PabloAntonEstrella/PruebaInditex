package com.example.pricingservice.model;

import java.time.LocalDateTime;

public class PriceRequest {

    private Integer brandId;
    private Integer productId;
    private LocalDateTime applicationDate;

    public PriceRequest() {
    }

    public PriceRequest(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        this.brandId = brandId;
        this.productId = productId;
        this.applicationDate = applicationDate;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
}
