package com.backend.tcc.agendamentos.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "agendamentos_servicos")
@Table(name = "agendamentos_servicos")
public class AgendamentoServicosEntity 
{
	@EmbeddedId
	private AgendamentoServicosEntityPK id;
	
	@SuppressWarnings("serial")
	@Embeddable
	public static class AgendamentoServicosEntityPK implements Serializable
	{
		private Long idAgendamento;
		private Long idServicos;

		public AgendamentoServicosEntityPK() 
		{
			
		}
		
		public AgendamentoServicosEntityPK( Long idAgendamento, Long idServicos ) 
		{
			this.idAgendamento = idAgendamento;
			this.idServicos = idServicos;
		}

		public Long getIdAgendamento() {
			return idAgendamento;
		}

		public void setIdAgendamento(Long idAgendamento) {
			this.idAgendamento = idAgendamento;
		}

		public Long getIdServicos() {
			return idServicos;
		}

		public void setIdServicos(Long idServicos) {
			this.idServicos = idServicos;
		}
	}
	
	public AgendamentoServicosEntity() 
	{
		
	}
	
	public AgendamentoServicosEntity( Long idUsuario, Long idPerfil ) 
	{
		this.id = new AgendamentoServicosEntityPK(idUsuario, idPerfil);
	}

	public AgendamentoServicosEntityPK getId() {
		return id;
	}

	public void setId(AgendamentoServicosEntityPK id) {
		this.id = id;
	}	
}
