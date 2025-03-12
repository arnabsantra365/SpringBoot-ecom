package com.ecommerce.order.orderline.service;

import com.ecommerce.order.order.Repo.OrderRepo;
import com.ecommerce.order.order.exception.BusinessException;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.orderline.model.OrderLine;
import com.ecommerce.order.orderline.repo.OrderLineRepo;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepo repository;
    private final OrderLineMapper mapper;
    private final OrderRepo orderRepo;

    public OrderLineService(OrderLineRepo repository, OrderLineMapper mapper, OrderRepo repo) {
        this.repository = repository;
        this.mapper = mapper;
        this.orderRepo=repo;
    }

    public Integer saveOrderLine(OrderLineRequest request) {
        OrderLine orderLine;

        if (request.id() != null) {
            orderLine = repository.findById(request.id())
                    .orElseThrow(() -> new BusinessException("OrderLine with ID " + request.id() + " does not exist."));
        } else {
            orderLine = new OrderLine();
        }

        // Fetch the Order entity before assigning
        Order order = orderRepo.findById(request.orderId())
                .orElseThrow(() -> new BusinessException("Order with ID " + request.orderId() + " not found."));

        // Map fields
        orderLine.setOrder(order);
        orderLine.setProductId(request.productId());
        orderLine.setQuantity(request.quantity());

        return repository.save(orderLine).getId();
    }
//public Integer saveOrderLine(OrderLineRequest request) {
//    var order = mapper.toOrderLine(request);
//    return repository.save(order).getId();
//}
}
