package com.backend.tcc.agendamentos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.backend.tcc.agendamentos.enums.AgendamentosStatusEnum;
import com.backend.tcc.usuario.entity.UsuarioEntity;

public class AgendamentosTO {
	
	private Long id;
	private UsuarioEntity usuario;	
	private Date dataAgendamento;
	private String nomeCliente;
	private String telefone;
	private AgendamentosStatusEnum status;
	private List<Long> servicos = new ArrayList<>();
	private String valorFinal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UsuarioEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
	public Date getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public AgendamentosStatusEnum getStatus() {
		return status;
	}
	public void setStatus(AgendamentosStatusEnum status) {
		this.status = status;
	}
	public List<Long> getServicos() {
		return servicos;
	}
	public void setServicos(List<Long> servicos) {
		this.servicos = servicos;
	}
	public String getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
}
