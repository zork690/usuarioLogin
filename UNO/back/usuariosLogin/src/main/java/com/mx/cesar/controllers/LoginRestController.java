package com.mx.cesar.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.cesar.entities.Usuario;
import com.mx.cesar.services.IUsuarioDetailServicio;
import com.mx.cesar.services.JwtService;
import com.mx.cesar.services.UserDetailServiceImpl;
import com.mx.cesar.services.UsuarioDetails;

@RestController
public class LoginRestController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginRestController.class);
	
	@Autowired
	private IUsuarioDetailServicio usuarioDetailService;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/usuario")
	public ResponseEntity<?> ingresaret()  {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		Optional<Usuario> u = null;
		
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			  u = this.usuarioDetailService.buscarUsuarioPorNombre(username);
			  log.error("USUARIO LOGUEADO: "+u.get());
			} else {
			  username = principal.toString();
			}
		
		
		
		
		return ResponseEntity.ok(u.get());
	}

}
