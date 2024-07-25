package com.backend.tcc.agendamentos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.backend.tcc.agendamentos.enums.AgendamentosStatusEnum;
import com.backend.tcc.servicos.entity.ServicosEntity;
import com.backend.tcc.usuario.entity.UsuarioEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "agendamentos")
@Table(name = "agendamentos")
public class AgendamentosEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuario;
	
	private Date dataAgendamento;
	private String nomeCliente;
	private String telefone;
	private String valorFinal;
	private AgendamentosStatusEnum status;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name="agendamentos_servicos", joinColumns = {@JoinColumn(name="idAgendamento")}, inverseJoinColumns = {@JoinColumn(name="idServicos")})
	private List<ServicosEntity> servicos = new ArrayList<>();
	
	public AgendamentosEntity() 
	{
		
	}
	
	public AgendamentosEntity( UsuarioEntity usuario, Date dataAgendamento, String nomeCliente, String telefone, AgendamentosStatusEnum status, List<ServicosEntity> servicos, String valorFinal) 
	{
		this.usuario = usuario;
		this.dataAgendamento = dataAgendamento;
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
		this.status = status;
		this.valorFinal = valorFinal;
		this.servicos = servicos;
	}
	
	public AgendamentosEntity( Long id, UsuarioEntity usuario, Date dataAgendamento, String nomeCliente, String telefone, AgendamentosStatusEnum status, List<ServicosEntity> servicos, String valorFinal) 
	{
		this.id = id;
		this.usuario = usuario;
		this.dataAgendamento = dataAgendamento;
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
		this.status = status;
		this.valorFinal = valorFinal;
		this.servicos = servicos;
	}
	
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

	public List<ServicosEntity> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicosEntity> servicos) {
		this.servicos = servicos;
	}

	public String getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
}
