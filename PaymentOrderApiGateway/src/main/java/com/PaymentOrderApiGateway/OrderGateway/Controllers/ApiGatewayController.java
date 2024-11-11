package com.PaymentOrderApiGateway.OrderGateway.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class ApiGatewayController {

		@GetMapping("/paymentFallback")
		public Mono<String> paymentFallback(){
			
			return Mono.just("payment service is down, try aftersome time");
		}
		
		@GetMapping("/orderFallback")
		public Mono<String> orderFallback(){
			
			return Mono.just("order service is down, try aftersome time");
		}
}
