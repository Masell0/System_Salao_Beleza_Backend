package com.backend.tcc.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.tcc.usuario.entity.UsuarioEntity;
import com.backend.tcc.usuario.service.UsuarioService;
import com.backend.tcc.usuario.to.LoginTO;
import com.backend.tcc.usuario.to.UsuarioTO;

@RestController
@RequestMapping(value = "/usuario", produces = "application/json")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public UsuarioEntity criarOuAtualizar(@RequestBody UsuarioTO usuario) throws Exception
	{
		return usuarioService.criarOuAtualizar(usuario);
	}
	
	@GetMapping
	public List<UsuarioEntity> listar()
	{
		return usuarioService.listar();
	}
	
	@PostMapping("/login")
	public UsuarioEntity login(@RequestBody LoginTO login) throws Exception
	{
		return usuarioService.login(login);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Long idUsuario)
	{
		usuarioService.delete(idUsuario);
	}
}
