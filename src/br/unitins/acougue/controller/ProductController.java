package br.unitins.acougue.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.controller.listener.ProductListener;
import br.unitins.acougue.model.MeatType;
import br.unitins.acougue.model.Product;

@Named
@ViewScoped
public class ProductController extends Controller<Product> {

	private static final long serialVersionUID = 4570628857407841810L;

	private String search;
	private List<Product> listProduct;

	public void openProductListener() {
		ProductListener listener = new ProductListener();
		listener.open();
	}
	
	public void getProductListener(SelectEvent event) {
		Product entity = (Product) event.getObject();
		setEntity(entity);
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