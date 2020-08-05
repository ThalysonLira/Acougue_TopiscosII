package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.model.Meat;
import br.unitins.acougue.model.Product;

public class MeatRepository extends Repository<Product> {

	public List<Meat> findByName(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT m ");
		jpql.append("FROM Meat m ");
		jpql.append("WHERE upper(m.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}

	
	public List<Meat> findByCut(String cut) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT m ");
		jpql.append("FROM Meat m ");
		jpql.append("WHERE upper(m.cut) ");
		jpql.append("LIKE upper(:cut) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("cut", "%" + cut + "%");

		return query.getResultList();
	}

	public List<Meat> findByAnimal(String animal) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT m ");
		jpql.append("FROM Meat m ");
		jpql.append("WHERE upper(m.animal) ");
		jpql.append("LIKE upper(:animal) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("animal", "%" + animal + "%");

		return query.getResultList();
	}

	public List<Meat> findByCutOrAnimal(String search) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT m ");
		jpql.append("FROM Meat m ");
		jpql.append("WHERE upper(m.cut) ");
		jpql.append("LIKE upper(:cut) ");
		jpql.append("AND upper(m.animal) ");
		jpql.append("LIKE upper(:animal) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("cut", "%" + search + "%");
		query.setParameter("animal", "%" + search + "%");

		return query.getResultList();
	}
	
}