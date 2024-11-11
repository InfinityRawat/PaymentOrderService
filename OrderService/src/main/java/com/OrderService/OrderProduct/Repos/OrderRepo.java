package com.OrderService.OrderProduct.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OrderService.OrderProduct.Entity.OrderEntity;


public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {

}
