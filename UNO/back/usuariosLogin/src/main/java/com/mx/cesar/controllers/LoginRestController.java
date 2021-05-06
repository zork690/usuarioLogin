package com.mx.cesar.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.cesar.entities.Usuario;
import com.mx.cesar.services.JwtService;
import com.mx.cesar.services.UserDetailServiceImpl;

@RestController
public class LoginRestController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginRestController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	JwtService jwtService;
	
	//@PreAuthorize("authenticated")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/ingresar")
	public ResponseEntity<?> ingresar(@RequestBody Usuario usuario) throws Exception {
		Usuario u = this.userDetailServiceImpl.buscarUsuarioPorNombre(usuario.getUsername());
		log.error("Usuario: "+u);
		if(u != null) {
			log.error("PASS ENCODED: "+new BCryptPasswordEncoder().encode(u.getClave()));
			this.authenticate(usuario.getUsername(), u.getClave());
		}
		return ResponseEntity.ok("fdg");
	}
	
	//@PreAuthorize("authenticated")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/ingresar")
	public String ingresaret()  {
		return "fdg";
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
		throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
		throw new Exception("INVALID_CREDENTIALS", e);
		}
		}

}
