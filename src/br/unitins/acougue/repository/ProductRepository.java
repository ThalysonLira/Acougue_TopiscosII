package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;
import br.unitins.acougue.model.Product;

public class ProductRepository extends Repository<Product> {

	public List<Product> findByName(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Product p ");
		jpql.append("WHERE upper(p.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}

	public List<Product> findByLot(String lot) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Product p ");
		jpql.append("WHERE upper(p.lot) ");
		jpql.append("LIKE upper(:lot) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("lot", "%" + lot + "%");

		return query.getResultList();
	}

	public List<Product> findByDescription(String description) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Product p ");
		jpql.append("WHERE upper(p.description) ");
		jpql.append("LIKE upper(:description) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("description", "%" + description + "%");

		return query.getResultList();
	}
	
	public List<Product> findByAvailable(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Product p ");
		jpql.append("WHERE upper(p.name) = upper(:name) ");
		jpql.append("AND p.availableQuantity >= 1 ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}
	
}