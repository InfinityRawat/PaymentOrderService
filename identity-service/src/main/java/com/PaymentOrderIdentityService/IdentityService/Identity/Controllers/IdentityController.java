package com.PaymentOrderIdentityService.IdentityService.Identity.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.PaymentOrderIdentityService.IdentityService.Entity.UserCredential;
import com.PaymentOrderIdentityService.IdentityService.Identity.Dtos.AuthRequest;
import com.PaymentOrderIdentityService.IdentityService.Identity.Services.CustomUserDetails;
import com.PaymentOrderIdentityService.IdentityService.Identity.Services.IdentityService;
import com.PaymentOrderIdentityService.IdentityService.Identity.Services.JwtService;

@RestController
@RequestMapping("/auth")
public class IdentityController {

	
		private AuthenticationManager auth;
		private IdentityService service;
		private JwtService jwtService;
		private CustomUserDetails userInfo;
	
		public IdentityController(AuthenticationManager auth, IdentityService service, JwtService jwtService,
				CustomUserDetails userInfo) {
			super();
			this.auth = auth;
			this.service = service;
			this.jwtService = jwtService;
			this.userInfo = userInfo;
		}


		@PostMapping("/register")
		@ResponseStatus(value = HttpStatus.CREATED)
		public void saveUser(@RequestBody UserCredential user) {
			service.saveUser(user);
		}
		
		
		@GetMapping("/generateToken")
		public String generateToken(@RequestBody AuthRequest authReq) {
				Authentication authenticate = auth.authenticate(new UsernamePasswordAuthenticationToken(authReq.username(),authReq.password()));
				return jwtService.generateToken(userInfo.loadUserByUsername(authReq.username()));	
		}
		
		@GetMapping("/validate")
		@ResponseStatus(value = HttpStatus.ACCEPTED)
		public String validateToken(@RequestParam String token) {
			jwtService.validateToken(token);
			return "Token is Valid";
		}
		
}
