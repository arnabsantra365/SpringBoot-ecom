package com.ecommerce.customer.service;

import com.ecommerce.customer.models.Customer;
import com.ecommerce.customer.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if(customerRepo.findByEmail(request.email())==null) {
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
}
