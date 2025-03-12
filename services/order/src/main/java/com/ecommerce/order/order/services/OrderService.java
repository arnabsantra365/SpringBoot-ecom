package com.ecommerce.order.order.services;

import com.ecommerce.order.customer.CustomerClient;
import com.ecommerce.order.kafka.OrderConfirmation;
import com.ecommerce.order.kafka.OrderProducer;
import com.ecommerce.order.order.Repo.OrderRepo;
import com.ecommerce.order.order.exception.BusinessException;
import com.ecommerce.order.orderline.service.OrderLineRequest;
import com.ecommerce.order.orderline.service.OrderLineService;
import com.ecommerce.order.product.ProductClient;
import com.ecommerce.order.product.PurchaseRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepo orderRepo, CustomerClient customerClient, ProductClient productClient, OrderMapper orderMapper, OrderLineService orderLineService,OrderProducer orderProducer) {
        this.orderRepo = orderRepo;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderMapper = orderMapper;
        this.orderLineService = orderLineService;
        this.orderProducer=orderProducer;
    }

    @Transactional
    public Integer createOrder(OrderRequest orderRequest) {
        var customer = customerClient.findCustomerById(orderRequest.customerId()).orElseThrow(()-> new BusinessException("No customer found"));
        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());
        var order = orderRepo.save(orderMapper.toOrder(orderRequest));
        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
//                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }
}
