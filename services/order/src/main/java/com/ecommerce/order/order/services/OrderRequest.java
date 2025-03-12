package com.ecommerce.order.order.services;

import com.ecommerce.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        Long amount,
//        @NotNull(message = "Payment method should be precised")
//        PaymentMethod paymentMethod,
        @Positive(message = "Customer should be present")
//        @NotBlank(message = "Customer should be present")
        Integer customerId,
        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequest> products
) {
}
