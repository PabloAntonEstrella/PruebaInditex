package com.example.pricingservice.controller;

import java.time.LocalDateTime;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.noContent;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pricingservice.entity.Price;
import com.example.pricingservice.exception.PricingServiceBadRequestException;
import com.example.pricingservice.model.EventMessage;
import com.example.pricingservice.model.PriceRequest;
import com.example.pricingservice.model.PriceResponse;
import com.example.pricingservice.service.PriceService;
import com.example.pricingservice.service.PriceServiceImpl;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v1/inditex")
@Validated
public class PriceController {
	
	@Autowired
    private PriceService priceService;
	private static final Logger log = LogManager.getLogger();
	private static final Marker JSON_EVENT = MarkerManager.getMarker("JSON_EVENT");

    @PostMapping("/price")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
							@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
							@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
							@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    public ResponseEntity<PriceResponse> getPrice(@RequestBody PriceRequest request) {

    	String eventId = "UUID";
        try{
            log.info(JSON_EVENT, new EventMessage(
                    eventId,
                    "PRICE_QUERY_INIT",
                    String.format("{\"brandId\": %d, \"productId\": %d, \"applicationDate\": \"%s\"}", request.getBrandId(), request.getProductId(), request.getApplicationDate()),
                    LocalDateTime.now(),
                    "PricingService"));
            Optional<PriceResponse> price = priceService.getApplicablePrice(request.getBrandId(), 
												            		request.getProductId(), 
												            		request.getApplicationDate());
            if(price.isPresent()) {
                log.info(JSON_EVENT, new EventMessage(
                        eventId,
                        "PRICE_FOUND",
                        String.format("{\"productId\": %d, \"price\": %.2f }", request.getProductId(), price.get().getFinalPrice()),
                        LocalDateTime.now(),
                        "PricingService"));
                return ok(price.get());
            }else{
                log.warn(JSON_EVENT, new EventMessage(
                        eventId,
                        "PRICE_NOT_FOUND",
                        String.format("{\"productId\": %d }", request.getProductId()),
                        LocalDateTime.now(),
                        "PricingService"));
                return noContent().build();
            }

       }catch (Exception e) {
            log.error(JSON_EVENT, new EventMessage(
                    eventId,
                    "INTERNAL_ERROR",
                    String.format("{\"message\": \"%s\" }", e.getMessage()),
                    LocalDateTime.now(),
                    "PricingService"));
            throw new PricingServiceBadRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}