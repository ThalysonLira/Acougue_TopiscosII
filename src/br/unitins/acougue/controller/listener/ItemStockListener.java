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
import br.unitins.acougue.model.ItemStock;
import br.unitins.acougue.repository.ItemStockRepository;

@Named
@ViewScoped
public class ItemStockListener extends Listener<ItemStock> {

	private static final long serialVersionUID = -3326688224192816093L;
	private List<ItemStock> list;
	private String search;
	
	public void search() {
		ItemStockRepository repository = new ItemStockRepository();
		setList(repository.findByName(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((ItemStock) em.find(ItemStock.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/listener/estoque_lista", options, null);
	}

	@Override
	public ItemStock getEntity() {
		if (entity == null)
			entity = new ItemStock();
		return entity;
	}

	public List<ItemStock> getList() {
		if (list == null)
			list = new ArrayList<ItemStock>();
		return list;
	}

	public void setList(List<ItemStock> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}