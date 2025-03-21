package com.ecommerce.customer.service;

import com.ecommerce.customer.models.Address;
import com.ecommerce.customer.models.Customer;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class CustomerMapper {

    public Customer toCustomer(@Valid CustomerRequest request) {
         final BCryptPasswordEncoder passwordencoder = new BCryptPasswordEncoder();

        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .password(passwordencoder.encode(request.password()))
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
