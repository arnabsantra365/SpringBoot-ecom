package com.ecommerce.product.services;

public record ProductPurchaseResponse (
        Integer productId,
        String name,
        String description,
        Long price,
        double quantity
){
}
