package br.unitins.acougue.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.controller.listener.ItemStockListener;
import br.unitins.acougue.model.ItemStock;
import br.unitins.acougue.model.Product;

@Named
@ViewScoped
public class ItemStockController extends Controller<ItemStock> {

	private static final long serialVersionUID = -4855085041221455440L;

	private String search;
	private List<ItemStock> stock;

	public void openItemStockListener() {
		ItemStockListener listener = new ItemStockListener();
		listener.open();
	}
	
	public void getItemStockListener(SelectEvent event) {
		ItemStock entity = (ItemStock) event.getObject();
		setEntity(entity);
	}

	public void getProductListener(SelectEvent event) {
		Product product = (Product) event.getObject();
		getEntity().setProduct(product);
		getEntity().setName(product.getCut() + " - " + product.getAnimal());
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