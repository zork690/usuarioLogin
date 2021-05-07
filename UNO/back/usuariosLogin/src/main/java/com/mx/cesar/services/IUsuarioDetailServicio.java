package com.mx.cesar.services;

import java.util.Optional;

import com.mx.cesar.entities.Usuario;

public interface IUsuarioDetailServicio {
	
	public Optional<Usuario> buscarUsuarioPorNombre(String nombre);

}
