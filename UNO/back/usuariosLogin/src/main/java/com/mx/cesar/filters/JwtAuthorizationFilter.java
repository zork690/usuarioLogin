package com.mx.cesar.filters;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mx.cesar.controllers.LoginRestController;
import com.mx.cesar.daos.IUsuarioDao;
import com.mx.cesar.entities.Usuario;
import com.mx.cesar.exceptions.JwtException;
import com.mx.cesar.services.JwtService;
import com.mx.cesar.services.UserDetailServiceImpl;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	public static final String AUTHORIZATION = "Authorization";
	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

	@Autowired
	JwtService jwtService;

	@Autowired
	UserDetailServiceImpl usuarioDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			log.error("EN EL FILTRO JWT...");
			String autHeader = request.getHeader(this.AUTHORIZATION);
			//if (this.jwtService.isBearer(autHeader)) {

				log.error("EN EL FILTRO JWT...");
				List<GrantedAuthority> authorities = this.jwtService.roles(autHeader).stream()
						.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						this.jwtService.user(autHeader), null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			//}
			filterChain.doFilter(request, response);
		} catch (JwtException e) {
			log.error("ERROR EN EL FILTRO JWT.. "+e.getMessage());
		}
	}

}
