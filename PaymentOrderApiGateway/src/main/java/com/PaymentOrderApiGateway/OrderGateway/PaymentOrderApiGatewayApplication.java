package com.PaymentOrderApiGateway.OrderGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentOrderApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentOrderApiGatewayApplication.class, args);
	}
	
	

}
