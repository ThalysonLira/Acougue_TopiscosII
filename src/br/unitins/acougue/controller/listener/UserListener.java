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
import br.unitins.acougue.model.User;
import br.unitins.acougue.repository.UserRepository;

@Named @ViewScoped
public class UserListener extends Listener<User> {

	private static final long serialVersionUID = 7140437483597011217L;
	private List<User> list;
	private String search;
	
	public void search() {
		UserRepository repository = new UserRepository();
		setList(repository.findByEmail(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((User) em.find(User.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/pages/listener/usuario_lista", options, null);
	}

	@Override
	public User getEntity() {
		if (entity == null)
			entity = new User();
		return entity;
	}

	public List<User> getList() {
		if (list == null)
			list = new ArrayList<User>();
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}