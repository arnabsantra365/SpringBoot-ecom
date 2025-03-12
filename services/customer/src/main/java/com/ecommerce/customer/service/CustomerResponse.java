package com.ecommerce.customer.service;

import com.ecommerce.customer.models.Address;

public record CustomerResponse(
        Integer id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
