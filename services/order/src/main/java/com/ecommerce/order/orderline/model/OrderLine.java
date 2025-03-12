package com.ecommerce.order.orderline.model;

import com.ecommerce.order.order.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "orderline")
@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;

    public OrderLine() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    private OrderLine(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
    }

    // ✅ Static Builder Class
    public static class Builder {
        private Integer id;
        private Order order;
        private Integer productId;
        private double quantity;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderLine build() {
            return new OrderLine(this);
        }
    }

    // ✅ Method to get a new Builder instance
    public static Builder builder() {
        return new Builder();
    }
}
