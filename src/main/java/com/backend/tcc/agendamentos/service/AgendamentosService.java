package com.backend.tcc.agendamentos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.tcc.agendamentos.entity.AgendamentosEntity;
import com.backend.tcc.agendamentos.enums.AgendamentosStatusEnum;
import com.backend.tcc.agendamentos.to.AgendamentosTO;
import com.backend.tcc.agendamentos.to.FiltroRelatorioAgendamentoTO;
import com.backend.tcc.perfil.service.PerfilService;
import com.backend.tcc.servicos.service.ServicosService;
import com.backend.tcc.usuario.service.UsuarioService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class AgendamentosService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ServicosService servicosService;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Transactional
	public AgendamentosEntity criarOuAtualizar(AgendamentosTO agendamento)
	{
		return entityManager.merge( 
				new AgendamentosEntity(
						agendamento.getId(),
						agendamento.getUsuario(),
						agendamento.getDataAgendamento(),
						agendamento.getNomeCliente(),
						agendamento.getTelefone(),
						agendamento.getStatus(),
						servicosService.getServicos(agendamento.getServicos()),
						agendamento.getValorFinal()
						));
	}
	
	@Transactional
	public void atualizarStatus(Long idAgendamento, Integer status)
	{
		AgendamentosEntity agendamento = entityManager.find(AgendamentosEntity.class, idAgendamento);
		agendamento.setStatus(AgendamentosStatusEnum.get(status));
		
		entityManager.merge(agendamento);
	}
	
	@SuppressWarnings("unchecked")
	public List<AgendamentosEntity> listarPorUsuario(Long idUsuario)
	{
		boolean temPerfilCliente = perfilService.temPerfilCliente(usuarioService.getUsuarioPorId(idUsuario).getPerfil());

		String query = new StringBuilder()
	            .append("SELECT a FROM agendamentos a")
	            .append(temPerfilCliente ? " WHERE usuario.id = :idUsuario" : "")
	            .append(" ORDER BY status, dataAgendamento ASC")
	            .toString();

	    Query em = entityManager.createQuery(query);
	    
	    if (temPerfilCliente) em.setParameter("idUsuario", idUsuario);
	    
	    return em.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<AgendamentosEntity> gerarRelatorio(FiltroRelatorioAgendamentoTO filtro) {
	    boolean temFiltro = filtro.getDataFinal() != null || filtro.getDataInicial() != null || !filtro.getServicos().isEmpty() || !filtro.getStatus().isEmpty();

	    StringBuilder queryBuilder = new StringBuilder("SELECT DISTINCT a FROM agendamentos a JOIN a.servicos s");

	    if (temFiltro) {
	        queryBuilder.append(" WHERE");
	        boolean hasPreviousCondition = false;

	        if (filtro.getDataInicial() != null) {
	            queryBuilder.append(" a.dataAgendamento > :dataInicial");
	            hasPreviousCondition = true;
	        }

	        if (filtro.getDataFinal() != null) {
	            if (hasPreviousCondition) queryBuilder.append(" AND");
	            queryBuilder.append(" a.dataAgendamento < :dataFinal");
	            hasPreviousCondition = true;
	        }

	        if (!filtro.getServicos().isEmpty()) {
	            if (hasPreviousCondition) queryBuilder.append(" AND"); 
	            queryBuilder.append(" s.id IN :idAgendamento"); 
	            hasPreviousCondition = true;
	        }

	        if (!filtro.getStatus().isEmpty()) {
	            if (hasPreviousCondition) queryBuilder.append(" AND"); 
	            queryBuilder.append(" a.status IN :status");
	            hasPreviousCondition = true; 
	        }
	    }

	    queryBuilder.append(" ORDER BY status, dataAgendamento ASC");

	    Query em = entityManager.createQuery(queryBuilder.toString());

	    if (filtro.getDataInicial() != null) em.setParameter("dataInicial", filtro.getDataInicial());
	    if (filtro.getDataFinal() != null) em.setParameter("dataFinal", filtro.getDataFinal());
	    if (!filtro.getServicos().isEmpty()) em.setParameter("idAgendamento", filtro.getServicos());
	    if (!filtro.getStatus().isEmpty()) em.setParameter("status", filtro.getStatus());
	    

	    return em.getResultList();
	}
}
