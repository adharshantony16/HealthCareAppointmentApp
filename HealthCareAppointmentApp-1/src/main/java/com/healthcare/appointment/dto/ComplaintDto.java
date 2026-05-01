package com.healthcare.appointment.dto;

import jakarta.validation.constraints.NotBlank;

public class ComplaintDto {
    @NotBlank(message = "Message is required")
    private String message;

    // Getters and setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}