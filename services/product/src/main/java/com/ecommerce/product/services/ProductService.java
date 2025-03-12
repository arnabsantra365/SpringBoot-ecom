package com.ecommerce.product.services;

import com.ecommerce.product.exception.ProductPurchaseException;
import com.ecommerce.product.repos.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

//    public ProductService(ProductRepository productRepository, ProductMapper mapper) {
//        this.productRepository = productRepository;
//        this.mapper = mapper;
//    }

    @Transactional
    public String createProduct( ProductRequest productRequest) {
        productRepository.save(mapper.toProduct(productRequest));
        return "product saved";
    }
//    @Transactional
    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product not found with id:"+productId));
    }

    public List<ProductPurchaseResponse> purchaseProduct(@Valid List<ProductPurchaseRequest> productPurchaseRequest) {
        var productIds = productPurchaseRequest
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var productStored = productRepository.findAllByIdInOrderById(productIds);
        if (productStored.size() != productIds.size()) {
            throw new EntityNotFoundException("Some product are missing");
        }
        var userProductList = productPurchaseRequest.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < productStored.size(); i++) {
            var product = productStored.get(i);
            var productRequest = userProductList.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(mapper.toProductPurchase(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }
}
