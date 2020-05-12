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
import br.unitins.acougue.model.Purchase;
import br.unitins.acougue.repository.PurchaseRepository;

@Named
@ViewScoped
public class PurchaseListener extends Listener<Purchase> {

	private static final long serialVersionUID = -1271519062853554361L;
	private List<Purchase> list;
	private String search;
	
	public void search() {
		PurchaseRepository repository = new PurchaseRepository();
		setList(repository.findByItem(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((Purchase) em.find(Purchase.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/listener/item_venda_lista", options, null);
	}

	@Override
	public Purchase getEntity() {
		if (entity == null)
			entity = new Purchase();
		return entity;
	}

	public List<Purchase> getList() {
		if (list == null)
			list = new ArrayList<Purchase>();
		return list;
	}

	public void setList(List<Purchase> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}