package com.backend.tcc.usuario.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "usuario_perfil")
@Table(name = "usuario_perfil")
public class UsuarioPerfilEntity {
	
	@EmbeddedId
	private UsuarioPerfilPK id;
	
	@SuppressWarnings("serial")
	@Embeddable
	public static class UsuarioPerfilPK implements Serializable
	{
		private Long idUsuario;
		private Long idPerfil;

		public UsuarioPerfilPK() 
		{
			
		}
		
		public UsuarioPerfilPK( Long idUsuario, Long idPerfil ) 
		{
			this.idUsuario = idUsuario;
			this.idPerfil = idPerfil;
		}

		public Long getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(Long idUsuario) {
			this.idUsuario = idUsuario;
		}

		public Long getIdPerfil() {
			return idPerfil;
		}

		public void setIdPerfil(Long idPerfil) {
			this.idPerfil = idPerfil;
		}
	}
	
	public UsuarioPerfilEntity() 
	{
		
	}
	
	public UsuarioPerfilEntity( Long idUsuario, Long idPerfil ) 
	{
		this.id = new UsuarioPerfilPK(idUsuario, idPerfil);
	}

	public UsuarioPerfilPK getId() {
		return id;
	}

	public void setId(UsuarioPerfilPK id) {
		this.id = id;
	}	
}
