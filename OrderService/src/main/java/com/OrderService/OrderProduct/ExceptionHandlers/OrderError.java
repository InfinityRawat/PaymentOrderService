package com.OrderService.OrderProduct.ExceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderError {

		public int httpStatus;
//		public String HttpStatusCode;
		public String description;
		
}
