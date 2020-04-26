package br.unitins.acougue.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.Provider;
import br.unitins.acougue.model.Situation;

@Named
@ViewScoped
public class ProviderController extends Controller<Provider>{

	private static final long serialVersionUID = 3679724066632322580L;

	private String search;
	private List<Provider> listProvider;
	private boolean userCreation;
	
	public void search() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery(
				"SELECT p "
			  + "FROM Provider p "
			  + "WHERE upper(p.name) LIKE upper(:search)"
			  + "OR p.cnpj LIKE :search");
		query.setParameter("search", "%"+ getSearch() + "%");
		// TODO Criar maskCnpj
		//query.setParameter("search", "%" + Util.maskCpf(search) + "%");
		listProvider = query.getResultList();
	}
	
	public Situation[] getListSituation() {
		return Situation.values();
	}
	
	@Override
	public Provider getEntity() {
		if (entity == null)
			entity = new Provider();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Provider> getListProvider() {
		return listProvider;
	}

	public void setListProvider(List<Provider> listProvider) {
		this.listProvider = listProvider;
	}

	public boolean isUserCreation() {
		return userCreation;
	}

	public void setUserCreation(boolean userCreation) {
		this.userCreation = userCreation;
	}

}