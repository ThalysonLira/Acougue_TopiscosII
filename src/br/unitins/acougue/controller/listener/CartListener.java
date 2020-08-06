package br.unitins.acougue.controller.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.PrimeFaces;

import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.Cart;
import br.unitins.acougue.repository.CartRepository;

@Named @ViewScoped
public class CartListener extends Listener<Cart> {

	private static final long serialVersionUID = -1271519062853554361L;
	private List<Cart> list;
	private String search;
	
	public void search() {
		CartRepository repository = new CartRepository();
		setList(repository.findByClient(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((Cart) em.find(Cart.class, id));
		
		PrimeFaces.current().dialog().closeDynamic(getEntity());
	}
	
	public void open() {
		Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 800);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");  

        PrimeFaces.current().dialog().openDynamic("/listener/carrinho_lista", options, null);
	}

	@Override
	public Cart getEntity() {
		if (entity == null)
			entity = new Cart();
		return entity;
	}

	public List<Cart> getList() {
		if (list == null)
			list = new ArrayList<Cart>();
		return list;
	}

	public void setList(List<Cart> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}