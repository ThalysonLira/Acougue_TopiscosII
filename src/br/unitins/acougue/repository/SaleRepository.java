package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.model.Sale;

public class SaleRepository extends Repository<Sale> {
	
	public List<Sale> findByBuyer(String buyer) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT s ");
		jpql.append("FROM Sale s ");
		jpql.append("WHERE upper(s.buyer.name) ");
		jpql.append("LIKE upper(:buyer) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("buyer", "%" + buyer + "%");

		return query.getResultList();
	}

	public List<Sale> findBySalesman(String salesman) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT s ");
		jpql.append("FROM Sale s ");
		jpql.append("WHERE upper(s.salesman.name) ");
		jpql.append("LIKE upper(:salesman) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("salesman", "%" + salesman + "%");

		return query.getResultList();
	}
	
	public List<Sale> findByBuyerOrSalesman(String search) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT s ");
		jpql.append("FROM Sale s ");
		jpql.append("WHERE upper(s.buyer.name) ");
		jpql.append("LIKE upper(:search) ");
		jpql.append("OR upper(s.salesman.name) ");
		jpql.append("LIKE upper(:search) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("search", "%" + search + "%");

		return query.getResultList();
	}
	
}