package com.food.ordering.system.service.domain.event;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent implements DomainEvent<Order> {
        private final Order order;
        private final ZonedDateTime createdAt;

        public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
                this.order = order;
                this.createdAt = createdAt;
        }

        public Order getOrder() {
                return order;
        }

        public ZonedDateTime getCreatedAt() {
                return createdAt;
        }
}
