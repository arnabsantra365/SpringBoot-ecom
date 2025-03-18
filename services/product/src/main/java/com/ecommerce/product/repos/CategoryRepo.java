package com.ecommerce.product.repos;

import com.ecommerce.product.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Optional<Category> findByName(String name);
}
