package com.PaymentService.PaymentProduct.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PaymentService.PaymentProduct.Entity.PaymentEntity;
import com.PaymentService.PaymentProduct.Service.PaymentService;

@RestController
@RequestMapping("/paymentControl")
public class PaymentController {

		@Autowired
		private PaymentService serv;
		
		@PostMapping("api/v1/doPayment")
		public ResponseEntity<PaymentEntity> doPayment(@RequestBody PaymentEntity pay){
			PaymentEntity doPayment = serv.doPayment(pay);
			
			return new ResponseEntity<>(doPayment,HttpStatus.ACCEPTED);
		}
		
		
		@GetMapping("api/v1/payments/{orderID}")
		public ResponseEntity<PaymentEntity> getOrderPaymentDetails(@PathVariable Integer orderID){
			PaymentEntity doPayment = serv.findPayment(orderID);
			return new ResponseEntity<>(doPayment,HttpStatus.ACCEPTED);
		}
}
