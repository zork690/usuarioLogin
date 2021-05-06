package com.mx.cesar.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mx.cesar.exceptions.JwtException;
import io.jsonwebtoken.*;

@Service
public class JwtService {
	
	/*@Autowired
	private AuthenticationManager authenticationManager;*/

	private static final String BEARER = "Bearer ";
	private static final String USER = "user";
	private static final String ROLES = "roles";
	private static final String ISSUER = "miw-spring5";
	private static final int EXPIRES_IN_MILLISECONDS = 3600000;
	private static final String SECRET = "clave-secreta-test";
	
	public String createToken(String user, List<String> roles) {
		return JWT.create()
				.withIssuer(this.ISSUER)
				.withIssuedAt(new Date())
				.withNotBefore(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + this.EXPIRES_IN_MILLISECONDS ))
				.withClaim(this.USER, user)
				.withArrayClaim(this.ROLES, roles.toArray(new String[0]))
				.sign(Algorithm.HMAC256(this.SECRET));
	}
	
	public boolean isBearer(String authorization) {
		return authorization != null && authorization.startsWith(this.BEARER) && authorization.split("\\.").length == 3;
	}
	
	public String user(String authorization) throws JwtException {
		return this.verify(authorization).getClaim(this.USER).asString();
	}
	
	private DecodedJWT verify(String authorization) throws JwtException {
		if(!this.isBearer(authorization)) {
			throw new JwtException("No es Bearer ");
		}
		
		try {
			
			return JWT.require(Algorithm.HMAC256(this.SECRET))
					.withIssuer(this.ISSUER).build()
					.verify(authorization.substring(this.BEARER.length()));
			
		}catch(Exception e) {
			throw new JwtException("JWT is wrong. "+e.getMessage());
		}
	}
	
	public List<String> roles(String authorization) throws JwtException {
		return Arrays.asList(this.verify(authorization).getClaim(this.ROLES).asArray(String.class));
	}
	
	/*public void autenticar(String usuario, String pass) throws Exception {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario, pass));
		}catch(DisabledException e) {
			throw new Exception("USER DISABLED ",e);
		}catch(BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS ",e);
		}
	}*/
	
}
