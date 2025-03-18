package com.ecommerce.product.services;

import com.ecommerce.product.models.Category;
import com.ecommerce.product.models.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct( ProductRequest productRequest,Category category) {
        return Product.builder().id(productRequest.id())
                                .name(productRequest.name()).description(productRequest.description()).availableQuantity(productRequest.availableQuantity()).price(productRequest.price())
                .category(category)
                                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName()
//                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchase(Product product,double quantity){
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
