package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.model.City;

public class CityRepository extends Repository<City> {
	
	public List<City> findByName(String name) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM City c ");
		jpql.append("WHERE upper(c.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}
	
	public List<City> findByAbbreviation(String abbreviation) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM City c ");
		jpql.append("WHERE upper(c.abbreviation) ");
		jpql.append("LIKE upper(:abbreviation) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("abbreviation", "%" + abbreviation + "%");

		return query.getResultList();
	}
	
	public List<City> findByState(String state) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM City c ");
		jpql.append("WHERE upper(c.state.name) ");
		jpql.append("LIKE upper(:state) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("state", "%" + state + "%");

		return query.getResultList();
	}

}