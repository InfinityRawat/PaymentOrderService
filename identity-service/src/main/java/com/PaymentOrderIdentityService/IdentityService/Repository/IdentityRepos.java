package com.PaymentOrderIdentityService.IdentityService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PaymentOrderIdentityService.IdentityService.Entity.UserCredential;

public interface IdentityRepos extends JpaRepository<UserCredential	, Integer> {

		Optional<UserCredential> findByName(String name);
}
