package com.ecommerce.customer.repo;

import com.ecommerce.customer.models.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,String> {
    Customer findByEmail(String email);
}
