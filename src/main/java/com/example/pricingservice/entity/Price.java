package com.example.pricingservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Integer productId;
    private Integer priority;
    private Double price;
    private String curr;

    // Constructor vacío necesario para JPA
    public Price() {
    }

    // Constructor con todos los campos
    public Price(Integer brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList, Integer productId, Integer priority, Double price, String curr) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }
}