package com.ecommerce.customer.service;

import com.ecommerce.customer.models.Customer;
import com.ecommerce.customer.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class CustomerService {
//    @Autowired
    private final CustomerRepo customerRepo;
//    @Autowired
    private final CustomerMapper mapper;



    public CustomerService(CustomerRepo customerRepo, CustomerMapper mapper) {
        this.customerRepo = customerRepo;
        this.mapper = mapper;
    }
    public String createCustomer(@Valid CustomerRequest request) {

        Customer user = customerRepo.findByEmail(request.email());
        if(user==null) {
            var customer = customerRepo.save(mapper.toCustomer(request));
            System.out.println(customer.getId());
        }
        else{
            return "Email already present";
        }
        return "succesfull request";
    }

    public CustomerResponse findById(String customerId) {
        return this.customerRepo.findById(customerId)
                .map(mapper::fromCustomer).orElseThrow(()-> new RuntimeException("Customer not found"));
    }

    public List<CustomerResponse> findAllCustomers() {
        return  customerRepo.findAll()
                .stream()
                .map(this.mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> loginCustomer(LoginRequest loginRequest) {
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Customer user = customerRepo.findByEmail(loginRequest.email());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // Creating a response object excluding the password for security reasons
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("customerId", user.getId());
        response.put("firstName", user.getFirstname());
        response.put("lastName", user.getLastname());
        response.put("email", user.getEmail());
        response.put("address", user.getAddress());

        return ResponseEntity.ok(response);
    }

}
