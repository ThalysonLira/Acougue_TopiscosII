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
import br.unitins.acougue.model.PhysicalClient;
import br.unitins.acougue.repository.PhysicalClientRepository;
import br.unitins.acougue.model.Person;

@Named @ViewScoped
public class PhysicalClientListener extends Listener<Person>{

	private static final long serialVersionUID = -8692343065470325600L;
	private List<PhysicalClient> list;
	private String search;
	
	public void search() {
		PhysicalClientRepository repository = new PhysicalClientRepository();
		setList(repository.findByNameOrCpf(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((PhysicalClient) em.find(PhysicalClient.class, id));
		
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
	public PhysicalClient getEntity() {
		if (entity == null)
			entity = new PhysicalClient();
		return (PhysicalClient) entity;
	}

	public List<PhysicalClient> getList() {
		if (list == null)
			list = new ArrayList<PhysicalClient>();
		return list;
	}

	public void setList(List<PhysicalClient> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}