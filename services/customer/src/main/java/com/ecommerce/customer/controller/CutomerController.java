package com.ecommerce.customer.controller;

import com.ecommerce.customer.models.Customer;
import com.ecommerce.customer.service.CustomerRequest;
import com.ecommerce.customer.service.CustomerResponse;
import com.ecommerce.customer.service.CustomerService;
import com.ecommerce.customer.service.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin("*")
@RequestMapping("/v1/api/customer")
//@RequiredArgsConstructor
public class CutomerController {
//    @Autowired
    private final CustomerService customerService;

    public CutomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(
            @RequestBody LoginRequest loginRequest
            ){

        return ResponseEntity.ok(customerService.loginCustomer(loginRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }
}
