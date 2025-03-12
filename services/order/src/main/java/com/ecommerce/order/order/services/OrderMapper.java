package com.ecommerce.order.order.services;

import com.ecommerce.order.order.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        if (request == null) {
            return null;
        }
        return Order.builder()
//                .id(request.id())
                .reference(request.reference())
//                .paymentMethod(request.paymentMethod())
                .totalAmount(request.amount())
                .customerId(request.customerId())
                .build();
    }
}
