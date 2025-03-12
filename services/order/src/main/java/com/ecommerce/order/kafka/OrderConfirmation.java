package com.ecommerce.order.kafka;

import com.ecommerce.order.customer.CustomerResponse;
import com.ecommerce.order.product.PurchaseResponse;

import java.io.Serializable;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        Long totalAmount,
//        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) implements Serializable {
}
