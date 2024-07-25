package com.backend.tcc.agendamentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.tcc.agendamentos.entity.AgendamentosEntity;
import com.backend.tcc.agendamentos.service.AgendamentosService;
import com.backend.tcc.agendamentos.to.AgendamentosTO;
import com.backend.tcc.agendamentos.to.FiltroRelatorioAgendamentoTO;

@RestController
@RequestMapping(value = "/agendamentos", produces = "application/json")
public class AgendamentosController {
	
	@Autowired
	private AgendamentosService agendamentosService;
	
	@GetMapping("/{idUsuario}")
	public List<AgendamentosEntity> listarPorUsuario(@PathVariable(name = "idUsuario") Long idUsuario)
	{
		return agendamentosService.listarPorUsuario(idUsuario);
	}
	
	@PostMapping
	public AgendamentosEntity criarOuAtualizar(@RequestBody AgendamentosTO agendamento)
	{
		return agendamentosService.criarOuAtualizar(agendamento);
	}
	
	@PostMapping("/relatorios")
	public List<AgendamentosEntity> gerarRelatorio(@RequestBody FiltroRelatorioAgendamentoTO filtro)
	{
		return agendamentosService.gerarRelatorio( filtro );
	}
}
