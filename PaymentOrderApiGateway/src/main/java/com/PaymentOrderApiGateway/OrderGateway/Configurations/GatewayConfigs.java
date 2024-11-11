package com.PaymentOrderApiGateway.OrderGateway.Configurations;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.PaymentOrderApiGateway.OrderGateway.AuthenticationFilter;

@Configuration
public class GatewayConfigs {
	
	private  AuthenticationFilter authenticationFilter;
	
	

	public GatewayConfigs(AuthenticationFilter authenticationFilter) {
		super();
		this.authenticationFilter = authenticationFilter;
	}



	@Bean
	RouteLocator creatingRouteCircuitBreaker(RouteLocatorBuilder builder) {
		return builder.routes()
		.route("PAYMENTSERVICE",p->p.path("/paymentControl/**")
				.filters(
						filter-> filter.filter(authenticationFilter
								.apply(new AuthenticationFilter.Config()))
								.circuitBreaker(fallback->fallback.setName("gatewayBreaker")
										.setFallbackUri("forward:/paymentFallback"))).uri("lb://PaymentService"))

			.route("ORDERSERVICE",p->p.path("/orders/**")
				
				.filters(filter->filter.filter(authenticationFilter.apply(new AuthenticationFilter.Config())).circuitBreaker(fallback->fallback.setName("gatewayBreaker").setFallbackUri("forward:/orderFallback")))
				.uri("lb://OrderService"))
		.route("IdentityService",p->p.path("/auth/**").uri("lb://IdentityService"))
		.build();
	}

}
