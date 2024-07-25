package com.backend.tcc.agendamentos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.backend.tcc.agendamentos.enums.AgendamentosStatusEnum;

public class FiltroRelatorioAgendamentoTO {
	private Date dataInicial;
	private Date dataFinal;
	private List<Long> servicos;
	private List<AgendamentosStatusEnum> status;
	
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public List<Long> getServicos() {
		return servicos;
	}
	public void setServicos(List<Long> servicos) {
		this.servicos = servicos;
	}
	public List<AgendamentosStatusEnum> getStatus() {
		return status;
	}
	public void setStatus(List<AgendamentosStatusEnum> status) {
		this.status = status;
	}
	
	public List<Integer> getStatusIds() {
		List<Integer> list = new ArrayList<Integer>();
		this.status.forEach( status -> { list.add(status.ID); });
		
		return list;
	}
}
