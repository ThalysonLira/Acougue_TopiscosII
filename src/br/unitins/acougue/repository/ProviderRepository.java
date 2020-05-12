package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.Provider;

public class ProviderRepository extends Repository<Provider>{

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
		query.setParameter("cnpj", "%" + Util.maskCnpj(cnpj) + "%");

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
		query.setParameter("search", "%" + Util.maskCnpj(search) + "%");

		return query.getResultList();
	}	
	
}