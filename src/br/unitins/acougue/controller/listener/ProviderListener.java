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
import br.unitins.acougue.model.Person;
import br.unitins.acougue.model.Provider;
import br.unitins.acougue.repository.ProviderRepository;

@Named @ViewScoped
public class ProviderListener extends Listener<Person> {

	private static final long serialVersionUID = 1374787629948314086L;
	private List<Provider> list;
	private String search;
	
	public void search() {
		ProviderRepository repository = new ProviderRepository();
		setList(repository.findByNameOrCnpj(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((Provider) em.find(Provider.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/pages/listener/fornecedor_lista", options, null);
	}

	@Override
	public Provider getEntity() {
		if (entity == null)
			entity = new Provider();
		return (Provider) entity;
	}

	public List<Provider> getList() {
		if (list == null)
			list = new ArrayList<Provider>();
		return list;
	}

	public void setList(List<Provider> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}