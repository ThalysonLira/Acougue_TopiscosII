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
import br.unitins.acougue.model.Sale;
import br.unitins.acougue.repository.SaleRepository;

@Named @ViewScoped
public class SaleListener extends Listener<Sale> {

	private static final long serialVersionUID = -4440907611439017130L;
	private List<Sale> list;
	private String search;
	
	public void search() {
		SaleRepository repository = new SaleRepository();
		setList(repository.findByBuyer(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((Sale) em.find(Sale.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/pages/listener/venda_lista", options, null);
	}

	@Override
	public Sale getEntity() {
		if (entity == null)
			entity = new Sale();
		return entity;
	}

	public List<Sale> getList() {
		if (list == null)
			list = new ArrayList<Sale>();
		return list;
	}

	public void setList(List<Sale> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}