package com.ecommerce.customer.service;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "email required")
        String email,
        @NotNull(message = "password required")
        String password
) {

}
