package com.PaymentOrderServiceDiscovery.ServiceDiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PaymentOrderServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentOrderServiceDiscoveryApplication.class, args);
	}

}
