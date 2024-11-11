package com.OrderService.OrderProduct.DTOs;

import com.OrderService.OrderProduct.Entity.OrderEntity;

public record TransactionResponse(OrderEntity order, double ammount, String transactionID, String message) {

}
