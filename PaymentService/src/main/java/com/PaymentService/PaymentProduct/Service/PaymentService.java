package com.PaymentService.PaymentProduct.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PaymentService.PaymentProduct.Entity.PaymentEntity;
import com.PaymentService.PaymentProduct.Repos.PaymentRepos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	@Autowired
	private PaymentRepos repo;
	
	public PaymentEntity doPayment(PaymentEntity payment) {
		
		log.info("payment processing ..........");
		payment.setTransactionId(UUID.randomUUID().toString());
		payment.setPaymentStatus(paymentProcessing());

		try {
			log.info("payment service request: {}", new ObjectMapper().writeValueAsString(payment));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			repo.save(payment);

			
			return payment;
	}
	
	
	public PaymentEntity findPayment(Integer id) {
		
			Optional<PaymentEntity> payment = repo.findById(id);
			try {
				log.info("payment service find history by id : {}", new ObjectMapper().writeValueAsString(payment.get()));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return payment.orElseThrow(()->new RuntimeException("Payment not found")) ;
	}

	private String paymentProcessing() {
		// TODO Auto-generated method stub
		return new Random().nextBoolean()?"false":"success";
	}
}
