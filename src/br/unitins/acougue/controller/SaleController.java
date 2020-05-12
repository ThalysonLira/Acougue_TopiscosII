package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.application.RepositoryException;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.controller.listener.SaleListener;
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

	public void openSaleListener() {
		SaleListener listener = new SaleListener();
		listener.open();
	}
	
	public void getSaleListener(SelectEvent event) {
		Sale entity = (Sale) event.getObject();
		setEntity(entity);
	}

	public void getClientListener(SelectEvent event) {
		// TODO adicionar verificação de usuário logado para cliente e funcionário
		Client client = (Client) event.getObject();
		getEntity().setBuyer(client);
//		entity.setSalesman("logUser");
	}

	public void getItemStockListener(SelectEvent event) {
		ItemStock item = (ItemStock) event.getObject();
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