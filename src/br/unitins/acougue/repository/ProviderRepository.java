package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.Person;
import br.unitins.acougue.model.Provider;

public class ProviderRepository extends Repository<Person> {

	public List<Provider> findByName(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Provider p ");
		jpql.append("WHERE upper(p.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}

	public List<Provider> findByCnpj(String cnpj) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Provider p ");
		jpql.append("WHERE p.cnpj ");
		jpql.append("LIKE :cnpj ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("cnpj", "%" + cnpj + "%");

		return query.getResultList();
	}

	public List<Provider> findByNameOrCnpj(String search) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Provider p ");
		jpql.append("WHERE upper(p.name) ");
		jpql.append("LIKE upper(:search) ");
		jpql.append("OR p.cnpj ");
		jpql.append("LIKE :search ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("search", "%" + search + "%");
		query.setParameter("search", "%" + search + "%");

		return query.getResultList();
	}
	
	public boolean contains(Integer id, String cnpj) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT COUNT(*) ");
		jpql.append("FROM Provider p ");
		jpql.append("WHERE p.cnpj = ? ");
		jpql.append("AND p.id <> ? ");
		
		Query query = getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter(1, Util.maskCnpj(cnpj));
		query.setParameter(2, id == null ? -1 : id);
		
		return (long) query.getSingleResult() == 0 ? false : true;
	}
	
}