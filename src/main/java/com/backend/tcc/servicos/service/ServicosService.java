package com.backend.tcc.servicos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.tcc.agendamentos.entity.AgendamentoServicosEntity;
import com.backend.tcc.commons.expections.BusinessValidationException;
import com.backend.tcc.servicos.entity.ServicosEntity;
import com.backend.tcc.usuario.entity.UsuarioServicoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ServicosService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<ServicosEntity> listar()
	{
		return entityManager.createQuery("SELECT s FROM servicos s").getResultList();
	}
	
	@Transactional
	public ServicosEntity criarOuAtualizar(ServicosEntity servico)
	{
		return entityManager.merge( servico );
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Long servicoId)
	{
		List<AgendamentoServicosEntity> la = entityManager.createQuery("SELECT 1 FROM agendamentos_servicos WHERE id.idServicos = :servicoId")
				.setParameter("servicoId", servicoId)
				.setMaxResults(1)
				.getResultList();
		
		if ( !la.isEmpty() ) {
			throw new BusinessValidationException("servico_vinculado");
		}
		
		List<UsuarioServicoEntity> lu = entityManager.createQuery("SELECT 1 FROM usuario_servicos WHERE id.idServicos = :servicoId")
				.setParameter("servicoId", servicoId)
				.setMaxResults(1)
				.getResultList();
		
		if ( !lu.isEmpty() ) {
			throw new BusinessValidationException("servico_vinculado");
		}
		
		entityManager.remove( entityManager.find(ServicosEntity.class, servicoId) );
	}
	
	@SuppressWarnings("unchecked")
	public List<ServicosEntity> getServicos(List<Long> servicos)
	{
		return entityManager.createQuery("SELECT s FROM servicos s where id in :servicos")
				.setParameter("servicos", servicos)
				.getResultList();
	}
}
