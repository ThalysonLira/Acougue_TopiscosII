package br.unitins.acougue.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.factory.MeatType;
import br.unitins.acougue.model.Product;

@Named
@ViewScoped
public class ProductController extends Controller<Product> {

	private static final long serialVersionUID = 4570628857407841810L;

	private String search;
	private List<Product> listProduct;

	public void search() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery("SELECT p " + "FROM Product p " + "WHERE upper(p.cut) LIKE upper(:search)"
				+ "OR upper(p.animal) LIKE upper(:search)");
		query.setParameter("search", "%" + getSearch() + "%");
		listProduct = query.getResultList();
	}

	@Override
	public Product getEntity() {
		if (entity == null)
			entity = new Product();
		return entity;
	}

	public MeatType[] getListType() {
		return MeatType.values();
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

}