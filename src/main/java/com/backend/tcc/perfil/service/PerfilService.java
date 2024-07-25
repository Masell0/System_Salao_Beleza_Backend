package com.backend.tcc.perfil.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.stereotype.Service;

import com.backend.tcc.perfil.entity.PerfilEntity;
import com.backend.tcc.perfil.enums.PerfilEnum;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class PerfilService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<PerfilEntity> listar() 
	{
		return entityManager.createQuery("SELECT p FROM perfil p").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<PerfilEntity> getPerfil(List<Long> perfil)
	{
		return entityManager.createQuery("SELECT s FROM perfil s where id in :perfil")
				.setParameter("perfil", perfil)
				.getResultList();
	}
	
	public boolean temPerfilCliente(List<PerfilEntity> perfils) 
	{
		AtomicBoolean temPerfilCliente = new AtomicBoolean(false);
		
		perfils.forEach(item -> {
			if( PerfilEnum.CLIENTE.ID.equals( item.getId().intValue() ) ) {
				temPerfilCliente.set(true);
			}
		});
		
		return temPerfilCliente.get();
	}
}
