package br.unitins.acougue.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.controller.listener.ItemListener;
import br.unitins.acougue.model.Item;
import br.unitins.acougue.model.Product;

@Named
@ViewScoped
public class StockController extends Controller<Item> {

	private static final long serialVersionUID = -4855085041221455440L;

	private String search;
	private List<Item> stock;

	public void openItemStockListener() {
		ItemListener listener = new ItemListener();
		listener.open();
	}
	
	public void getItemStockListener(SelectEvent event) {
		Item entity = (Item) event.getObject();
		setEntity(entity);
	}

	public void getProductListener(SelectEvent event) {
		Product product = (Product) event.getObject();
		getEntity().setProduct(product);
		getEntity().setName(product.getCut() + " - " + product.getAnimal());
	}

	@Override
	public Item getEntity() {
		if (entity == null)
			entity = new Item();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Item> getStock() {
		return stock;
	}

	public void setStock(List<Item> stock) {
		this.stock = stock;
	}

}