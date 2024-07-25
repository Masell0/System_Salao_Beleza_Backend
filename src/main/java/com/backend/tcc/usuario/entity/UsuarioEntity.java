package com.backend.tcc.usuario.entity;

import java.util.ArrayList;
import java.util.List;

import com.backend.tcc.perfil.entity.PerfilEntity;
import com.backend.tcc.servicos.entity.ServicosEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "usuario")
@Table(name = "usuario")
public class UsuarioEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String email;
	private String nome;
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="usuario_perfil", joinColumns = {@JoinColumn(name="idUsuario")}, inverseJoinColumns = {@JoinColumn(name="idPerfil")})
	private List<PerfilEntity> perfil = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="usuario_servicos", joinColumns = {@JoinColumn(name="idUsuario")}, inverseJoinColumns = {@JoinColumn(name="idServicos")})
	private List<ServicosEntity> servicos = new ArrayList<>();
	
	public UsuarioEntity() 
	{
		
	}
	
	public UsuarioEntity( String email, String nome, String password, List<ServicosEntity> servicos, List<PerfilEntity> perfil) 
	{
		this.email = email;
		this.nome = nome;
		this.password = password;
		this.servicos = servicos;
		this.perfil = perfil;
	}
	
	public UsuarioEntity( Long id, String email, String nome, String password, List<ServicosEntity> servicos, List<PerfilEntity> perfil) 
	{
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.password = password;
		this.servicos = servicos;
		this.perfil = perfil;
	}
	
	public Long getId() {
		return id;
	}
	public List<PerfilEntity> getPerfil() {
		return perfil;
	}
	public void setPerfil(List<PerfilEntity> perfil) {
		this.perfil = perfil;
	}
	public List<ServicosEntity> getServicos() {
		return servicos;
	}
	public void setServicos(List<ServicosEntity> servicos) {
		this.servicos = servicos;
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
}
