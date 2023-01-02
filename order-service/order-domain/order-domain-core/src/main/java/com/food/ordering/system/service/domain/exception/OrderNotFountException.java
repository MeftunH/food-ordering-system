package com.food.ordering.system.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class OrderNotFountException extends DomainException {
    public OrderNotFountException(String message) {
        super(message);
    }
}
