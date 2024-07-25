package com.backend.tcc.usuario.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "usuario_servicos")
@Table(name = "usuario_servicos")
public class UsuarioServicoEntity {
	
	@EmbeddedId
	private UsuarioServicoPK id;
	
	@SuppressWarnings("serial")
	@Embeddable
	public static class UsuarioServicoPK implements Serializable
	{
		private Long idUsuario;
		private Long idServicos;

		public UsuarioServicoPK() 
		{
			
		}
		
		public UsuarioServicoPK( Long idUsuario, Long idServicos ) 
		{
			this.idUsuario = idUsuario;
			this.idServicos = idServicos;
		}

		public Long getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(Long idUsuario) {
			this.idUsuario = idUsuario;
		}

		public Long getIdServicos() {
			return idServicos;
		}

		public void setIdServicos(Long idServicos) {
			this.idServicos = idServicos;
		}
		
	}
	
	public UsuarioServicoEntity() 
	{
		
	}
	
	public UsuarioServicoEntity( Long idUsuario, Long idServico ) 
	{
		this.id = new UsuarioServicoPK(idUsuario, idServico);
	}

	public UsuarioServicoPK getId() {
		return id;
	}

	public void setId(UsuarioServicoPK id) {
		this.id = id;
	}
	
	
}
