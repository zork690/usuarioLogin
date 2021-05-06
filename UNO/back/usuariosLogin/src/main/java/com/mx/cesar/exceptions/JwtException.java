package com.mx.cesar.exceptions;


public class JwtException extends Exception {

	public JwtException() {
		super();
	}
	
	public JwtException(String mensaje) {
		super(mensaje);
	}
	
	public JwtException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
	
	public JwtException(Throwable causa) {
		super(causa);
	}
}
