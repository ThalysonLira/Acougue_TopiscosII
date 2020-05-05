package br.unitins.acougue.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.Profile;
import br.unitins.acougue.model.User;

@Named
@ViewScoped
public class UserController extends Controller<User>{

	private static final long serialVersionUID = 3092155194023413476L;
	
	private String search;
	private List<User> listUser;
	
	public void search() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery(
				"SELECT u " + "FROM UserSystem u" + "WHERE upper(u.email) LIKE upper(:search)");
		query.setParameter("search", "%" + getSearch() + "%");
		listUser = query.getResultList();
	}
	
	public void getUserByProfile() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery(
				"SELECT u " + "FROM User u " + "WHERE u.profile LIKE :search");
		query.setParameter("search", "%" + getSearch() + "%");
		listUser = query.getResultList();
	}
	
	public Profile[] getListProfile() {
		return Profile.values();
	}

	@Override
	public User getEntity() {
		if (entity == null)
			entity = new User();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

}