package com.backend.tcc.perfil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.tcc.perfil.entity.PerfilEntity;
import com.backend.tcc.perfil.service.PerfilService;

@RestController
@RequestMapping(value = "/perfil", produces = "application/json")
public class PerfilController {
	
	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	public List<PerfilEntity> listar()
	{
		return perfilService.listar();
	}
}
