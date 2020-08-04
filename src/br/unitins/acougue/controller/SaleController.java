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
import br.unitins.acougue.model.Cart;
import br.unitins.acougue.model.Item;
import br.unitins.acougue.model.Product;
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
		getEntity().getCart().setClient(client);
//		entity.setSalesman("logUser");
	}

	public void getItemStockListener(SelectEvent event) {
		Product product = (Product) event.getObject();
		getEntity().getCart().getItems().add(new Item(product));
	}

	public void removePurchase(Item item) {
		getEntity().getCart().getItems().remove(item);
	}

	@Override
	public Sale getEntity() {
		if (entity == null)
			entity = new Sale();
		return entity;
	}

	@Override
	public void save() {
		if (getEntity().getCart().getItems().size() >= 1) {
			super.save();
		}
	}

	@Override
	public void delete() {
		super.delete();
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