package com.ecommerce.customer.service;

import com.ecommerce.customer.models.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;


public record CustomerRequest(
        Integer id,
        @NotNull(message="required firstname")
        String firstname,
        @NotNull(message="required lastname")
        String lastname,
        @NotNull(message="required email")
        @Email(message = "Not a valid email")
        String email,
        Address address,
        @NotNull(message="required password")
        String password
) {
}
