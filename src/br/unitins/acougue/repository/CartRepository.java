package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.model.Cart;
import br.unitins.acougue.model.Item;

public class CartRepository extends Repository<Cart> {

	public List<Cart> findByClient(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Cart c ");
		jpql.append("WHERE upper(c.client.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}

	public List<Item> findBySale(int sale) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Cart c ");
		jpql.append("WHERE c.sale.id ");
		jpql.append("LIKE :sale ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("sale", sale);

		return query.getResultList();
	}
	
}