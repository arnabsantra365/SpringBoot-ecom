package com.ecommerce.order.orderline.repo;

import com.ecommerce.order.orderline.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepo extends JpaRepository<OrderLine,Integer> {
}
