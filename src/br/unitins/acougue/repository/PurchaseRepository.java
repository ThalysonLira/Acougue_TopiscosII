package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.model.Purchase;
import br.unitins.acougue.model.User;

public class PurchaseRepository extends Repository<Purchase> {

	public List<Purchase> findByItem(String item) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Purchase p ");
		jpql.append("WHERE upper(p.item.name) ");
		jpql.append("LIKE upper(:item) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("item", "%" + item + "%");

		return query.getResultList();
	}
	
	public List<Purchase> findBySale(String sale) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT p ");
		jpql.append("FROM Purchase p ");
		jpql.append("WHERE p.sale ");
		jpql.append("LIKE :sale ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("sale", "%" + sale + "%");

		return query.getResultList();
	}
	
}