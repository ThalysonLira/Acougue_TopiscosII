package br.unitins.acougue.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.Cart;
import br.unitins.acougue.model.Item;
import br.unitins.acougue.model.Product;

@Named
@ViewScoped
public class CartController extends Controller<Cart> {

	private static final long serialVersionUID = -5796252711396995715L;

	private String search;
	private Item item;

	public void getProductListener(SelectEvent event) {
		Product product = (Product) event.getObject();
		getItem().setProduct(product);
		getEntity().getItems().add(getItem());

		setItem(null);
	}

	public void removePurchase(Item item) {
		getEntity().getItems().remove(item);
	}

	@Override
	public void save() {
		if (getEntity().getItems().size() >= 1)
			super.save();
		else
			Util.addMessageError("Carrinho vazio, impossível finalizar venda.");
	}

	@Override
	public Cart getEntity() {
		if (entity == null)
			entity = new Cart();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Item getItem() {
		if (item == null)
			item = new Item();
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}