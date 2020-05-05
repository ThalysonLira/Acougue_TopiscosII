package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.acougue.application.RepositoryException;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.Client;
import br.unitins.acougue.model.Purchase;
import br.unitins.acougue.model.ItemStock;
import br.unitins.acougue.model.Sale;
import br.unitins.acougue.repository.Repository;

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

	public void getPurchaseBySale() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery("SELECT p " + "FROM Purchase p " + "WHERE p.sale.id= :sale_id");
		query.setParameter("sale_id", getEntity().getId());
		getEntity().setListPurchase(query.getResultList());
	}

	public void addBuyer(Client u) {
		// TODO adicionar verificação de usuário logado para cliente e funcionário
		entity.setBuyer(u);
//		entity.setSalesman("logUser");
	}

	public void addPurchase(ItemStock item) {
		Purchase purchase = new Purchase();
		purchase.setItem(item);
		getEntity().getListPurchase().add(purchase);
	}

	public void removePurchase(Purchase item) {
		getEntity().getListPurchase().remove(item);
	}

	public void generateTotal() {
		Double sum = 0.0;
		for (Purchase p : entity.getListPurchase()) {
			sum += p.getQuantity() * p.getValue();
		}
		entity.setTotal(sum);
	}

	@Override
	public Sale getEntity() {
		if (entity == null)
			entity = new Sale();
		return entity;
	}

	@Override
	public void save() {
		// TODO remover itens do estoque
		Repository<Sale> rs = new Repository<Sale>();
		Repository<Purchase> rp = new Repository<Purchase>();
		Sale sale = getEntity();

		try {
			rs.beginTransaction();
			rs.save(getEntity());
			sale.setId(rs.refresh(getEntity()).getId());
			rs.commitTransaction();

			rp.beginTransaction();
			for (Purchase p : sale.getListPurchase()) {
				p.setSale(sale);
				rp.save(p);
			}
			rp.commitTransaction();
		} catch (RepositoryException e) {
			e.printStackTrace();
			rs.rollbackTransaction();
			rp.rollbackTransaction();
			Util.addMessageError("Erro ao salvar.");
			setEntity(null);
		}

		this.clear();
		Util.addMessageInfo("Cadastro realizado com sucesso.");
	}

	@Override
	public void delete() {
		// TODO devolver itens ao estoque
		Repository<Sale> rs = new Repository<Sale>();
		Repository<Purchase> rp = new Repository<Purchase>();
		Sale sale = getEntity();

		try {
			rp.beginTransaction();
			for (Purchase p : sale.getListPurchase()) {
				p.setSale(sale);
				rp.delete(p);
			}
			rp.commitTransaction();
			
			rs.beginTransaction();
			rs.delete(getEntity());
			rs.commitTransaction();
		} catch (RepositoryException e) {
			e.printStackTrace();
			rs.rollbackTransaction();
			rp.rollbackTransaction();
			Util.addMessageError("Erro ao salvar.");
			return;
		}
		this.clear();
		Util.addMessageInfo("Exclusão realizada com sucesso.");
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