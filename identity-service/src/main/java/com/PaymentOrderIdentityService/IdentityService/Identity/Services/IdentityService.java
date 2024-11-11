package com.PaymentOrderIdentityService.IdentityService.Identity.Services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.PaymentOrderIdentityService.IdentityService.Entity.UserCredential;
import com.PaymentOrderIdentityService.IdentityService.Repository.IdentityRepos;

@Service
public class IdentityService {

	private IdentityRepos repo;
	private PasswordEncoder encode;


	
	
	public IdentityService(IdentityRepos repo, PasswordEncoder encode) {
		super();
		this.repo = repo;
		this.encode = encode;
	}

	public void saveUser(UserCredential user) {
		
		user.setPassword(encode.encode(user.getPassword()));
		
		repo.save(user);
	}
	
	public UserCredential getUser(String name) {
		Optional<UserCredential> byName = repo.findByName(name);
		return byName.orElseThrow(()-> new UsernameNotFoundException(name+" not available, please register yourself"));
	}
}
