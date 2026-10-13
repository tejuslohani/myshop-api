package com.myshop.business.dto;

import java.time.Instant;
import java.util.UUID;

public record BusinessResponse(
        UUID id,
        UUID ownerUserId,
        String name,
        String businessType,
        String phone,
        String email,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String postalCode,
        String country,
        boolean active,
        Instant createdAt
) {
}