package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.model.Country;

public class CountryRepository extends Repository<Country> {
	
	public List<Country> findByName(String name) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Country c ");
		jpql.append("WHERE upper(c.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}
	
	public List<Country> findByAbbreviation(String abbreviation) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Country c ");
		jpql.append("WHERE upper(c.abbreviation) ");
		jpql.append("LIKE upper(:abbreviation) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("abbreviation", "%" + abbreviation + "%");

		return query.getResultList();
	}

}