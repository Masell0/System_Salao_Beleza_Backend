package com.backend.tcc.servicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.tcc.servicos.entity.ServicosEntity;
import com.backend.tcc.servicos.service.ServicosService;

@RestController
@RequestMapping(value = "/servicos", produces = "application/json")
public class ServicosController {
	@Autowired
	private ServicosService servicosService;
	
	@GetMapping
	public List<ServicosEntity> listar()
	{
		return servicosService.listar();
	}
	
	@PostMapping
	public ServicosEntity criarOuAtualizar(@RequestBody ServicosEntity servicos)
	{
		return servicosService.criarOuAtualizar(servicos);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Long servicosId)
	{
		servicosService.delete(servicosId);
	}
}
