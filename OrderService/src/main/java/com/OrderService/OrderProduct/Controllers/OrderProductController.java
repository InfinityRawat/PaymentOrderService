package com.OrderService.OrderProduct.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderService.OrderProduct.DTOs.TransactionRequest;
import com.OrderService.OrderProduct.DTOs.TransactionResponse;
import com.OrderService.OrderProduct.Entity.OrderEntity;
import com.OrderService.OrderProduct.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderProductController {

	@Autowired
	private OrderService serv;
	
	@PostMapping("/api/v1/save")
	public ResponseEntity<TransactionResponse> saveOrder(@RequestBody TransactionRequest dto){
		TransactionResponse saveEmployee = serv.saveEmployee(dto);
		return new ResponseEntity<>(saveEmployee,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/app/v1/orders/{id}")
	public ResponseEntity<OrderEntity> saveOrder(@PathVariable Integer id, @RequestHeader("userName") String authUser){
		OrderEntity oRder = serv.getORder(id);
		System.out.println("Authenticated User is "+ authUser);
		return new ResponseEntity<>(oRder,HttpStatus.ACCEPTED);
		
	}
}
