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
import br.unitins.acougue.model.Product;
import br.unitins.acougue.repository.ProductRepository;

@Named
@ViewScoped
public class ProductListener extends Listener<Product> {
	
	private static final long serialVersionUID = -3808967038976026818L;
	private List<Product> list;
	private String search;
	
	public void search() {
		ProductRepository repository = new ProductRepository();
		setList(repository.findByCutOrAnimal(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((Product) em.find(Product.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/listener/produto_lista", options, null);
	}

	@Override
	public Product getEntity() {
		if (entity == null)
			entity = new Product();
		return entity;
	}

	public List<Product> getList() {
		if (list == null)
			list = new ArrayList<Product>();
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}