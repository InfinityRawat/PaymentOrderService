package com.PaymentOrderApiGateway.OrderGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.PaymentOrderApiGateway.OrderGateway.Utils.GatewayUtils;
import com.PaymentOrderApiGateway.OrderGateway.Utils.JwtService;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> implements Ordered{

	@Autowired
	private JwtService jwtService;

	public AuthenticationFilter(JwtService jwtService) {
		super(Config.class);
		this.jwtService = jwtService;
	}

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {

		return (exchange, chain) -> {
			ServerHttpRequest newRequest =null;
			if (!GatewayUtils.openApiEndpoints.contains(exchange.getRequest().getURI().getPath())) {

				if (exchange.getRequest().getHeaders().containsKey("Authorization")) {
					String key = getAuthHeader(exchange.getRequest());

					if (key!=null && key.startsWith("Bearer ")) {
						key = key.substring(7);

						try {
							jwtService.validateToken(key);
							 newRequest= exchange.getRequest().mutate().header("userName", jwtService.extractUserName(key)).build();
						} catch (Exception ex) {
							System.out.println("Token not valid " + ex.getLocalizedMessage());
							throw new RuntimeException("Token not valid");
						}

					}

					else {

						throw new RuntimeException("Key not present!!");
					}
				}

				else {
					throw new RuntimeException("Header is not present in request!!");
				}
			}

			return chain.filter(exchange.mutate().request(newRequest).build());
		};
	}
	
	  private String getAuthHeader(ServerHttpRequest request) {
	        return request.getHeaders().getOrEmpty("Authorization").get(0);
	    }

	public static class Config {

	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return -2147483648;
	}

}
