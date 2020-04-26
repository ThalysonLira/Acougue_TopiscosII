package br.unitins.acougue.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.ItemStock;
import br.unitins.acougue.model.Product;

@Named
@ViewScoped
public class ItemStockController extends Controller<ItemStock> {

	private static final long serialVersionUID = -4855085041221455440L;

	private String search;
	private List<ItemStock> stock;

	public void search() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery("SELECT s " + "FROM Stock s " + "WHERE upper(s.name) LIKE upper(:search)");
		query.setParameter("search", "%" + getSearch() + "%");
		stock = query.getResultList();
	}

	public void getProductByAnimal() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery("SELECT p " + "FROM Product p " + "WHERE upper(p.cut) LIKE upper(:search)"
				+ "OR upper(p.animal) LIKE upper(:search)");
		query.setParameter("search", "%" + getSearch() + "%");
		stock = query.getResultList();
	}

	public void getProductByType() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery("SELECT p " + "FROM Product p " + "WHERE upper(p.cut) LIKE upper(:search)"
				+ "OR upper(p.animal) LIKE upper(:search)");
		query.setParameter("search", "%" + getSearch() + "%");
		stock = query.getResultList();
	}

	public void getProductByCategory() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery("SELECT p " + "FROM Product p " + "WHERE upper(p.cut) LIKE upper(:search)"
				+ "OR upper(p.animal) LIKE upper(:search)");
		query.setParameter("search", "%" + getSearch() + "%");
		stock = query.getResultList();
	}
	
	public void addProduct(Product p) {
		entity.setProduct(p);
		entity.setName(p.getCut() + " - " + p.getAnimal());
	}

	@Override
	public ItemStock getEntity() {
		if (entity == null)
			entity = new ItemStock();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<ItemStock> getStock() {
		return stock;
	}

	public void setStock(List<ItemStock> stock) {
		this.stock = stock;
	}

}