package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.ItemStock;

public class ItemStockRepository extends Repository<ItemStock>{

	public List<ItemStock> findByName(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT i ");
		jpql.append("FROM Stock i ");
		jpql.append("WHERE upper(i.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}

	public List<ItemStock> findByAnimal(String animal) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT i ");
		jpql.append("FROM Stock i ");
		jpql.append("WHERE upper(i.product.animal) ");
		jpql.append("LIKE upper(:animal) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("animal", "%" + animal + "%");

		return query.getResultList();
	}

	public List<ItemStock> findByType(String type) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT i ");
		jpql.append("FROM Stock i ");
		jpql.append("WHERE upper(i.type) ");
		jpql.append("LIKE upper(:type) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("type", "%" + type + "%");

		return query.getResultList();
	}

	public List<ItemStock> findByCategory(String category) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT i ");
		jpql.append("FROM Stock i ");
		jpql.append("WHERE upper(i.product.category) ");
		jpql.append("LIKE upper(:category) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("category", "%" + category + "%");

		return query.getResultList();
	}
	
}