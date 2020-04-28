package br.unitins.acougue.controller;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.Client;
import br.unitins.acougue.model.ItemSale;
import br.unitins.acougue.model.Sale;

@Named
@ViewScoped
public class SaleController extends Controller<Sale> {

	private static final long serialVersionUID = 3138471975776387061L;

	private String search;
	private List<Sale> listSale;

	public void search() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery("SELECT s " + "FROM Sale s " + "WHERE upper(s.buyer.name) LIKE upper(:search)");
		query.setParameter("search", "%" + getSearch() + "%");
		listSale = query.getResultList();
	}
	
	public void getSaleBySalesman() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery("SELECT s " + "FROM Sale s " + "WHERE upper(s.salesman.name) LIKE upper(:search)");
		query.setParameter("search", "%" + getSearch() + "%");
		listSale = query.getResultList();
	}

	public void addBuyer(Client u) {
		entity.setBuyer(u);
//		entity.setSalesman("logUser");
	}
	
	public void generateTotal() {
		Double sum = null;
		for (ItemSale i : entity.getListSale()) {
			sum += i.getValue();
		}
		entity.setTotal(sum);
	}
	
	public void currentDate() {
		Date today = new Date();
		entity.setSaleDate(today);
	}

	@Override
	public Sale getEntity() {
		if (entity == null)
			entity = new Sale();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Sale> getListSale() {
		return listSale;
	}

	public void setListSale(List<Sale> listSale) {
		if (listSale == null)
			listSale = new ArrayList<Sale>();
		this.listSale = listSale;
	}

}