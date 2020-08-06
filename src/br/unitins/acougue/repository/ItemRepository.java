package br.unitins.acougue.repository;

import java.util.List;
import javax.persistence.Query;
import br.unitins.acougue.model.Item;

public class ItemRepository extends Repository<Item> {

	public List<Item> findByItem(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT i ");
		jpql.append("FROM Item i ");
		jpql.append("WHERE upper(i.product.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}
	
}