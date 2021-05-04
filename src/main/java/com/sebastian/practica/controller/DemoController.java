package com.sebastian.practica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sebastian.practica.repo.ClientRepository;

@Controller
public class DemoController {
	
	@GetMapping("/listar")
	public String listar(Model model) {
		//lógica
		model.addAttribute("clientes", repo.findAll());
		return "lista";
	}
	
	@Autowired
	private ClientRepository repo;
}
