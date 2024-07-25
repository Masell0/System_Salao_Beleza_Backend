package com.backend.tcc.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.tcc.agendamentos.entity.AgendamentosEntity;
import com.backend.tcc.commons.expections.BusinessValidationException;
import com.backend.tcc.perfil.service.PerfilService;
import com.backend.tcc.servicos.service.ServicosService;
import com.backend.tcc.usuario.entity.UsuarioEntity;
import com.backend.tcc.usuario.to.LoginTO;
import com.backend.tcc.usuario.to.UsuarioTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UsuarioService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ServicosService servicosService;
	
	@Autowired
	private PerfilService perfilService;
	
	@Transactional
	public UsuarioEntity criarOuAtualizar(UsuarioTO usuario) throws Exception
	{
		if(usuario.getId() == null)
		{
			UsuarioEntity usuarioExistente = getUsuarioPorEmail( usuario.getEmail() );
			
			if ( usuarioExistente != null )
			{
				throw new BusinessValidationException("email", "ja_existe");
			}
		}

		return entityManager.merge( 
				new UsuarioEntity(
						usuario.getId(),
						usuario.getEmail(), 
						usuario.getNome(), 
						usuario.getPassword(), 
						servicosService.getServicos(usuario.getServicos()),
						perfilService.getPerfil(usuario.getPerfil())
						));
	}
	
	@Transactional
	public UsuarioEntity getUsuarioPorEmail(String email)
	{
		 List<UsuarioEntity> list = entityManager.createQuery("SELECT u FROM usuario u "
				+ "WHERE u.email = :email", UsuarioEntity.class)
				.setParameter("email", email)
				.setMaxResults(1)
				.getResultList();
		 
		 return list.size() > 0 ? list.get(0) : null; 
	}
	
	@Transactional
	public UsuarioEntity getUsuarioPorId(Long idUsuario)
	{
		return entityManager.find(UsuarioEntity.class, idUsuario);
	}
	
	@Transactional
	public UsuarioEntity login(LoginTO login) throws Exception 
	{
		UsuarioEntity usuario = getUsuarioPorEmail(login.getEmail());
		
		if (usuario == null) 
		{
			throw new BusinessValidationException("usuario", "nao_existe");
		}
		
		if (!login.getEmail().equals(usuario.getEmail()) || !login.getPassword().equals( usuario.getPassword() ) )//( EncryptionUtils.decrypt( usuario.getPassword() ) )) 
		{
			throw new BusinessValidationException("usuario", "usuario_invalido");
		}
		
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> listar() 
	{
		return entityManager.createQuery("SELECT u FROM usuario u").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Long usuarioId)
	{	
		List<AgendamentosEntity> l = entityManager.createQuery("SELECT 1 FROM agendamentos WHERE usuario.id = :usuarioId")
				.setParameter("usuarioId", usuarioId)
				.setMaxResults(1)
				.getResultList();
		
		if ( !l.isEmpty() ) {
			throw new BusinessValidationException("usuario_vinculado");
		}
		
		entityManager.remove( entityManager.find(UsuarioEntity.class, usuarioId) );
	}
}
