package com.example.pricingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PricingServiceBadRequestException extends ResponseStatusException {

    public PricingServiceBadRequestException(HttpStatus status, String message) {
        super(status, message);
    }

    public PricingServiceBadRequestException(HttpStatus status, String message, Throwable cause) {
        super(status, message, cause);
    }
}