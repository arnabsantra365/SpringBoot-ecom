package com.ecommerce.order.product;

public record PurchaseResponse (
        Integer productId,
        String name,
        String description,
        Long price,
        double quantity
){
}
