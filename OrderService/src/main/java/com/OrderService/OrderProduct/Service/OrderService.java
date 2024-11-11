package com.OrderService.OrderProduct.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.OrderService.OrderProduct.DTOs.TransactionRequest;
import com.OrderService.OrderProduct.DTOs.TransactionResponse;
import com.OrderService.OrderProduct.Entity.OrderEntity;
import com.OrderService.OrderProduct.Entity.PaymentEntity;
import com.OrderService.OrderProduct.ExceptionHandlers.ServiceNotAvailableException;
import com.OrderService.OrderProduct.Repos.OrderRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RefreshScope
public class OrderService {

		@Autowired
		private OrderRepo repo;
		@Autowired
		private RestTemplate restTemp;
		
		@Value("${services.urls.paymentUrl}")
		private String paymentUrl;
		
		@Value("${services.urls.MyUrl}")
		private String urlComingFrom;
		
		
		@CircuitBreaker(name = "paymentOrderBreaker", fallbackMethod = "paymentOrderFallback")
		public TransactionResponse saveEmployee(TransactionRequest dto) {
			
			log.info("Order is processing");
			OrderEntity order = dto.order();
			PaymentEntity payment = dto.payment();

	        payment.setOrderId(order.getId());
	        payment.setAmmount(order.getPrice()*order.getQuantity());
	        
//	        rest call
	        log.info("PAYMENTSERVICE");
	         
	        PaymentEntity postForObject = restTemp.postForObject(paymentUrl, payment, PaymentEntity.class);
	        try {
				log.info("Payment entity is , {}",new ObjectMapper().writeValueAsString(postForObject));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String status = postForObject.getPaymentStatus().equals("success")?"Payment and order processing is done":"Something went wrong in Rest Temp";
	        repo.save(dto.order());
	        TransactionResponse resp = new TransactionResponse(order, postForObject.getAmmount(), postForObject.getTransactionId(),status);
	        log.info("This url coming from--------------------- {}",urlComingFrom);
			log.info("Order submitted for "+resp);
			
			return resp;
			
		}
		
		@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
		public TransactionResponse paymentOrderFallback(TransactionRequest dto, Exception ex) {
			
			System.out.println("Payemnt SErvice is down");
			
			throw new ServiceNotAvailableException("payment service is down");
		
		}
		
		
		public OrderEntity getORder(int id) {
			Optional<OrderEntity> byId = repo.findById(id);
			
			log.info("order saved...");
			return byId.orElseThrow(()->new RuntimeException("Not found"));
		}
}
