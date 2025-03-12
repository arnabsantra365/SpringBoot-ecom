package com.ecommerce.order.order.Repo;

import com.ecommerce.order.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer> {
}
