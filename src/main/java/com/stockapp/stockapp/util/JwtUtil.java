package com.stockapp.stockapp.util;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.signing.key.secret}")
	private String clave;

	@Value("${jwt.token.expiration.in.seconds}")
	private Long tiempoExpiracion;
	
	public String getJWTToken(String username, Integer userId) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("token"+userId)
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.claim("userId", userId)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + this.tiempoExpiracion))
				.signWith(SignatureAlgorithm.HS512,
						this.clave.getBytes()).compact();

		return Utils.PREFIX + token;
	}
	
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.clave.getBytes()).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public Integer getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.clave.getBytes()).parseClaimsJws(token).getBody();
		return (Integer) claims.get("userId");
	}
	
	public Object getClaimsValueFromToken(String claim) {
		String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getHeader("Authorization");
		if(Objects.nonNull(token)) {
			Claims claims = Jwts.parser().setSigningKey(this.clave.getBytes()).parseClaimsJws(token.replace(Utils.PREFIX, "")).getBody();
			return claims.get(claim);
		}
		return null;
	}
	
	public String getSecret() {
		return this.clave;
	}
}
