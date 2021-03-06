package com.food.ordering.system.service;

import com.food.ordering.system.service.domain.OrderDomainService;
import com.food.ordering.system.service.domain.entity.Order;
import com.food.ordering.system.service.domain.entity.Product;
import com.food.ordering.system.service.domain.entity.Restaurant;
import com.food.ordering.system.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.service.domain.event.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    private static final String UTC = "UTC";

    @Override
    public OrderCreatedEvent validateInitializeOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order,restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} has been initialized", order.getId());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    //for cross check
    //n squared time complexity
    // TODO: 7/7/2022 refactor to hashmap for increase performance and decrease time complexity
    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(orderItem -> restaurant.getProducts().forEach(restaurantProduct ->{
            Product currentProduct = orderItem.getProduct();

            if (currentProduct.equals(restaurantProduct)) {
               currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(),
                                                              restaurantProduct.getPrice());
            }
        }));
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new IllegalArgumentException("Restaurant with id: " + restaurant.getId().getValue() + " is currently not active");
        }
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} has been paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} has been approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order with id: {} has been cancelled", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} has been cancelled", order.getId().getValue());
    }
}
