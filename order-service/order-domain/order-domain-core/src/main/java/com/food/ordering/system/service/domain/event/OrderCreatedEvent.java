package com.food.ordering.system.service.domain.event;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }
}
