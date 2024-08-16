package com.example.pricingservice.model;


import java.time.LocalDateTime;

public class EventMessage {

    private String eventId;
    private String eventType;
    private String payload;
    private LocalDateTime timestamp;
    private String source;

    public EventMessage() {
        // Constructor vacío
    }

    public EventMessage(String eventId, String eventType, String payload, LocalDateTime timestamp, String source) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.payload = payload;
        this.timestamp = timestamp;
        this.source = source;
    }

    // Getters y Setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", payload='" + payload + '\'' +
                ", timestamp=" + timestamp +
                ", source='" + source + '\'' +
                '}';
    }
}