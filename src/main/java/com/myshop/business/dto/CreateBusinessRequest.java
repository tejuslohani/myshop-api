package com.myshop.business.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateBusinessRequest(

        UUID ownerUserId,

        @NotBlank(message = "Business name is required")
        @Size(max = 150, message = "Business name cannot exceed 150 characters")
        String name,

        @Size(max = 50, message = "Business type cannot exceed 50 characters")
        String businessType,

        @Size(max = 20, message = "Phone cannot exceed 20 characters")
        String phone,

        @Email(message = "Invalid email format")
        @Size(max = 255, message = "Email cannot exceed 255 characters")
        String email,

        @Size(max = 255, message = "Address cannot exceed 255 characters")
        String addressLine1,

        @Size(max = 255, message = "Address cannot exceed 255 characters")
        String addressLine2,

        @Size(max = 100, message = "City cannot exceed 100 characters")
        String city,

        @Size(max = 100, message = "State cannot exceed 100 characters")
        String state,

        @Size(max = 20, message = "Postal code cannot exceed 20 characters")
        String postalCode
) {
}