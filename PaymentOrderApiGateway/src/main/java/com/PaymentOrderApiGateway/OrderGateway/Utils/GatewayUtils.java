package com.PaymentOrderApiGateway.OrderGateway.Utils;

import java.util.List;
import java.util.Set;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GatewayUtils {

	public static final Set<String> openApiEndpoints = Set.of("/auth/register","/auth/generateToken","/eureka");
	
}
