package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.model.State;

public class StateRepository extends Repository<State> {
	
	public List<State> findByName(String name) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT s ");
		jpql.append("FROM State s ");
		jpql.append("WHERE upper(s.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}
	
	public List<State> findByAbbreviation(String abbreviation) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT s ");
		jpql.append("FROM State s ");
		jpql.append("WHERE upper(s.abbreviation) ");
		jpql.append("LIKE upper(:abbreviation) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("abbreviation", "%" + abbreviation + "%");

		return query.getResultList();
	}
	
	public List<State> findByCapital(String capital) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT s ");
		jpql.append("FROM State s ");
		jpql.append("WHERE upper(s.capital.name) ");
		jpql.append("LIKE upper(:capital) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("capital", "%" + capital + "%");

		return query.getResultList();
	}
	
	public List<State> findByCountry(String country) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT s ");
		jpql.append("FROM State s ");
		jpql.append("WHERE upper(s.country.name) ");
		jpql.append("LIKE upper(:country) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("country", "%" + country + "%");

		return query.getResultList();
	}

}