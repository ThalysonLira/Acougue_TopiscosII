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
import br.unitins.acougue.model.Meat;
import br.unitins.acougue.model.Product;
import br.unitins.acougue.repository.MeatRepository;

@Named @ViewScoped
public class MeatListener extends Listener<Product> {
	
	private static final long serialVersionUID = -3808967038976026818L;
	private List<Meat> list;
	private String search;
	
	public void search() {
		MeatRepository repository = new MeatRepository();
		setList(repository.findByCutOrAnimal(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((Meat) em.find(Meat.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/pages/listener/produto_lista", options, null);
	}

	@Override
	public Meat getEntity() {
		if (entity == null)
			entity = new Meat();
		return (Meat) entity;
	}

	public List<Meat> getList() {
		if (list == null)
			list = new ArrayList<Meat>();
		return list;
	}

	public void setList(List<Meat> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}