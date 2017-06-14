package br.com.fiap.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.fiap.entity.Agenda;
import br.com.fiap.entity.Paciente;
import br.com.fiap.util.JpaUtil;

public class Dao {
	
	protected EntityManager em;

	public void adicionar(Agenda agenda) {
		try {
			em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(agenda);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Paciente pesquisar(String id) {
		
		Paciente paciente = null;
		try {
			em = JpaUtil.getEntityManager();
			Query query = em.createQuery("select p from Paciente p where cpf = :cpf");
			query.setParameter("cpf", id);
			paciente = (Paciente) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paciente;
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> listar() {
		
		Query query = null;
		try {
			em = JpaUtil.getEntityManager();
			query = em.createQuery("select p from Paciente p");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.getResultList();
	}
	
	public boolean isEmpty() {
		
		Query query = null;
		try {
			em = JpaUtil.getEntityManager();
			query = em.createQuery("select a from Agenda a");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return !query.getResultList().isEmpty();
	}
}