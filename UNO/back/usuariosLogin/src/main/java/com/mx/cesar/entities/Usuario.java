package com.mx.cesar.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@Column(name = "LOGIN")
	private String login;

	@Column(name = "CLAVE")
	private String clave;

	@Column(name = "NOMBRE")
	private String username;

	@Column(name = "CLIENTE")
	private String cliente;
	
	@Column(name = "FECHAMODIFICACION")
	private Date fechaModificacion;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", clave=" + clave + ", username=" + username + ", cliente=" + cliente
				+ ", fechaModificacion=" + fechaModificacion + "]";
	}
	
	
	

}
