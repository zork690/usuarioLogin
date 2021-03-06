package com.mx.cesar.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.cesar.entities.Usuario;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, String>{
	
	@Query("SELECT u FROM Usuario u WHERE u.username = :username")
	public Optional<Usuario> findByUsername(@Param("username") String username);

}
