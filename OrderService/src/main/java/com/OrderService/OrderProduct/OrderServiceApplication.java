package com.OrderService.OrderProduct;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

		@Bean
		@LoadBalanced
		RestTemplate restTemp(RestTemplateBuilder rest) {
			return rest.setConnectTimeout(Duration.ofMinutes(1)).setReadTimeout(Duration.ofMinutes(1)).build();
		}
}
