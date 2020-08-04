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
import br.unitins.acougue.model.LegalClient;
import br.unitins.acougue.model.Person;
import br.unitins.acougue.repository.LegalClientRepository;

@Named @ViewScoped
public class LegalClientListener extends Listener<Person>{

	private static final long serialVersionUID = -8692343065470325600L;
	private List<LegalClient> list;
	private String search;
	
	public void search() {
		LegalClientRepository repository = new LegalClientRepository();
		setList(repository.findByName(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((LegalClient) em.find(LegalClient.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/listener/cliente_lista", options, null);
	}

	@Override
	public LegalClient getEntity() {
		if (entity == null)
			entity = new LegalClient();
		return (LegalClient) entity;
	}

	public List<LegalClient> getList() {
		if (list == null)
			list = new ArrayList<LegalClient>();
		return list;
	}

	public void setList(List<LegalClient> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}