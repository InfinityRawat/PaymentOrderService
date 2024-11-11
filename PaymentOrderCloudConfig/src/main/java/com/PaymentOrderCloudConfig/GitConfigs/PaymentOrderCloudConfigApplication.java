package com.PaymentOrderCloudConfig.GitConfigs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class PaymentOrderCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentOrderCloudConfigApplication.class, args);
	}

}
