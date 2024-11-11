package com.PaymentService.PaymentProduct.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PaymentService.PaymentProduct.Entity.PaymentEntity;

public interface PaymentRepos extends JpaRepository<PaymentEntity, Integer> {

}
