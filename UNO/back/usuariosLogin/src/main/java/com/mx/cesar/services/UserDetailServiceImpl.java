package com.mx.cesar.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.cesar.daos.IUsuarioDao;
import com.mx.cesar.entities.Usuario;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	public Usuario buscarUsuarioPorNombre(String nombre) {
		return this.usuarioDao.findByUsername(nombre);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u = this.usuarioDao.findByUsername(username);
		
		return new UsuarioDetails(u);
				
		/*if (u == null)
			throw new UsernameNotFoundException("Could not find user");
		
			return new UsuarioDetails(u);*/
		
	}
	
	private User userBuilder(String usuario, String clave, String...roles) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(String role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
		}
		return new User(usuario, clave, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}
