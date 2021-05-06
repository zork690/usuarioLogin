package com.mx.cesar.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InicioController {

	//@RequestMapping(value="/", method = RequestMethod.GET)
	@GetMapping("/")
	public String inicio(Map<String, Object> datosPaVista) {
		datosPaVista.put("titulo", "LOGIN PARA USUARIOS ADEA");		
		return "inicio";
	}
	
	
}
