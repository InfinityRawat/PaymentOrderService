package com.OrderService.OrderProduct.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.OrderService.OrderProduct.Configurations.OrderTestConfig;
import com.OrderService.OrderProduct.DTOs.TransactionRequest;
import com.OrderService.OrderProduct.DTOs.TransactionResponse;
import com.OrderService.OrderProduct.Entity.OrderEntity;
import com.OrderService.OrderProduct.Service.OrderService;


@WebMvcTest(OrderProductController.class)
public class OrderControllerTest {

	@MockBean
	private OrderService service;
	
	@Autowired
	private MockMvc mvc;
	
	
	
	@Test
	void saveOrderTest() throws Exception {
		
		TransactionResponse ts=  new TransactionResponse(new OrderEntity(),1332.21,"Match","match");
		
			when(service.saveEmployee(any(TransactionRequest.class))).thenReturn(ts);
			
			mvc.perform(post("/orders/api/v1/save")
					.content("\"{\\n    \\\"order\\\":  {\\n\\\"id\\\":99,\\n\\\"name\\\":\\\"Laptop\\\",\\n\\\"quantity\\\":1,\\n\\\"price\\\": 230000\\n},\\n\\\"payment\\\":{}\\n}\""))
			.andExpect(status().isCreated());
	}
}
