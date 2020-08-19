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
import br.unitins.acougue.model.Country;
import br.unitins.acougue.repository.CountryRepository;

@Named @ViewScoped
public class CountryListener extends Listener<Country> {

	private static final long serialVersionUID = 6081492010469610150L;
	
	private String search;
	private List<Country> list;
	
	public void search() {
		CountryRepository repository = new CountryRepository();
		setList(repository.findByName(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity(em.find(Country.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/pages/listener/pais_lista", options, null);
	}

	@Override
	public Country getEntity() {
		if (entity == null)
			entity = new Country();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Country> getList() {
		if (list == null)
			list = new ArrayList<Country>();
		return list;
	}

	public void setList(List<Country> list) {
		this.list = list;
	}

}