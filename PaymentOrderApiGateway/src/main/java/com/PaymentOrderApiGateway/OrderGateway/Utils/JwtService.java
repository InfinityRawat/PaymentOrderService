package com.PaymentOrderApiGateway.OrderGateway.Utils;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private static final String SECRET_KEY;
	
	static {
		SECRET_KEY = "BF7FD11ACE545745B7BA1AF98B6F156D127BC7BB544BAB6A4FD74E4FC7";
	}

    private Key getSignInKey() {
        // decode SECRET_KEY
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

	public void validateToken(String token) {
		
		Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);

		
	}

	
//	   private boolean isTokenExpired(String token) {
//	        return extractExpiration(token).before(new Date());
//	    }
	
//	
	
	public String extractUserName(String token) {
		return extractClaim(token,Claims::getSubject);
		
	}
	   private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	   
	    private Claims extractAllClaims(String token) {
	        return Jwts
	                .parserBuilder()
	                .setSigningKey(getSignInKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }
	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	
}
