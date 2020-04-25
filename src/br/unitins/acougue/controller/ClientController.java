package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.Client;
import br.unitins.acougue.model.Sex;

@Named
@ViewScoped
public class ClientController extends Controller<Client> {

	private static final long serialVersionUID = -1443526557455688665L;

	private String search;
	private List<Client> listClient;
	private boolean userCreation;
	
	// TODO Retornar data formatada do BD
	public void search() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery(
				"SELECT c "
			  + "FROM Client c "
			  + "WHERE upper(c.name) LIKE upper(:search)"
			  + "OR c.cpf LIKE :search");
		query.setParameter("search", "%"+ getSearch() + "%");
		listClient = query.getResultList();
	}
	
	public Sex[] getListSex() {
		return Sex.values();
	}
	
	@Override
	public Client getEntity() {
		if (entity == null)
			entity = new Client();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	

	public boolean isUserCreation() {
		return userCreation;
	}

	public void setUserCreation(boolean userCreation) {
		this.userCreation = userCreation;
	}
	
	public boolean renderEmail() {
		if (userCreation == false)
			return true;
		else
			return false;
	}

	public List<Client> getListClient() {
		if (listClient == null)
			listClient = new ArrayList<Client>();
		return listClient;
	}
	
}