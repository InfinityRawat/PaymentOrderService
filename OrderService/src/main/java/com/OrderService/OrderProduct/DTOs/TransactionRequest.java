package com.OrderService.OrderProduct.DTOs;

import com.OrderService.OrderProduct.Entity.OrderEntity;
import com.OrderService.OrderProduct.Entity.PaymentEntity;

public record TransactionRequest(OrderEntity order, PaymentEntity payment) {

}
