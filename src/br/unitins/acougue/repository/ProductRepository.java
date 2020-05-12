package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.Product;

public class ProductRepository extends Repository<Product>{

	public List<Product> findByCut(String cut) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Product p ");
		jpql.append("WHERE upper(p.cut) ");
		jpql.append("LIKE upper(:cut) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("cut", "%" + cut + "%");

		return query.getResultList();
	}

	public List<Product> findByAnimal(String animal) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Product p ");
		jpql.append("WHERE upper(p.animal) ");
		jpql.append("LIKE upper(:animal) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("animal", "%" + animal + "%");

		return query.getResultList();
	}

	public List<Product> findByCutOrAnimal(String search) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Product p ");
		jpql.append("WHERE upper(p.cut) ");
		jpql.append("LIKE upper(:search) ");
		jpql.append("OR upper(p.animal) ");
		jpql.append("LIKE upper(:search) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("search", "%" + search + "%");

		return query.getResultList();
	}	
	
}