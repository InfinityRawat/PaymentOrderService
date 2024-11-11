package com.OrderService.OrderProduct.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentEntity {
	
	private int paymentId;
    private String paymentStatus;
    private String transactionId;
    private int orderId;
    private double ammount;
	

}
