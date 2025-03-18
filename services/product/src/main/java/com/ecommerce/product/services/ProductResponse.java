package com.ecommerce.product.services;

public record ProductResponse(Integer id,
                              String name,
                              String description,
                              double availableQuantity,
                              Long price,
                              Integer categoryId,
                              String categoryName
                         ) {
}
