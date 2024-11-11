package com.PaymentOrderIdentityService.IdentityService.Identity.Services;

import java.util.Optional;

import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.PaymentOrderIdentityService.IdentityService.Entity.UserCredential;
import com.PaymentOrderIdentityService.IdentityService.Repository.IdentityRepos;

@Component
public class CustomUserDetails implements UserDetailsService {

	private IdentityRepos repo;
	
	
	public CustomUserDetails(IdentityRepos repo) {
		super();
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredential> byName = repo.findByName(username);
		UserCredential cred = byName.orElseThrow(()->new UsernameNotFoundException(username+" is not register "));
		return User.builder().username(username).password(cred.getPassword()).build();
	}

		
}
