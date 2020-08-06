package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.Person;
import br.unitins.acougue.model.PhysicalClient;

public class PhysicalClientRepository extends Repository<Person> {

	public List<PhysicalClient> findByName(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE upper(c.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}

	public List<PhysicalClient> findByCpf(String cpf) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE c.cpf ");
		jpql.append("LIKE :cpf ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("cpf", "%" + cpf + "%");

		return query.getResultList();
	}
	
	public List<PhysicalClient> findByNameOrCpf(String search) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE c.name ");
		jpql.append("LIKE :name ");
		jpql.append("OR c.cpf ");
		jpql.append("LIKE :cpf ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + search + "%");
		query.setParameter("cpf", "%" + search + "%");

		return query.getResultList();
	}
	
	public boolean contains(Integer id, String search) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT COUNT(*) ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE c.cpf = ? ");
		jpql.append("AND c.id <> ? ");
		
		Query query = getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter(1, Util.maskCpf(search));
		query.setParameter(2, id == null ? -1 : id);
		
		return (long) query.getSingleResult() == 0 ? false : true;
	}

}