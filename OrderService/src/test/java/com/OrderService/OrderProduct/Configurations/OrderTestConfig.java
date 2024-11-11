package com.OrderService.OrderProduct.Configurations;

import java.time.Duration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class OrderTestConfig {

//	@Bean
	@LoadBalanced
	RestTemplate restTemp(RestTemplateBuilder rest) {
		return rest.setConnectTimeout(Duration.ofMinutes(1)).setReadTimeout(Duration.ofMinutes(1)).build();
	}
}
