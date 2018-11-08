package net.azurewebsites.fatecsjc.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

@Component
public class JWTUtil {
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken2(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS256, secret.getBytes())
				.compact();
	}
	
	public String generateToken(Authentication authentication) {
		UserSS user = (UserSS) authentication.getPrincipal();
		Date now = new Date();
		
		Date expire = new Date(now.getTime() + expiration);
		
		return Jwts.builder()
				.setSubject(Integer.toString(user.getId()))
				.setIssuedAt(new Date()).setExpiration(expire)
				.signWith(SignatureAlgorithm.HS256, secret.getBytes())
				.claim("email", user.getUsername())
				.claim("senha", user.getPassword())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.compact();
	}
	
	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
