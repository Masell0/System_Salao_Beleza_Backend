package com.backend.tcc.usuario.to;

import java.util.ArrayList;
import java.util.List;

public class UsuarioTO {
	
	private Long id;
	private String email;
	private String nome;
	private String password;
	private List<Long> perfil = new ArrayList<>();
	private List<Long> servicos = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Long> getPerfil() {
		return perfil;
	}
	public void setPerfil(List<Long> perfil) {
		this.perfil = perfil;
	}
	public List<Long> getServicos() {
		return servicos;
	}
	public void setServicos(List<Long> servicos) {
		this.servicos = servicos;
	}
	
	
}
