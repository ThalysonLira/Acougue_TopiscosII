package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.Employee;
import br.unitins.acougue.model.Sex;
import br.unitins.acougue.model.Situation;

@Named
@ViewScoped
public class EmployeeController extends Controller<Employee> {

	private static final long serialVersionUID = 1397513922277282373L;

	private String search;
	private List<Employee> listEmployee;
	private boolean userCreation;
	
	// TODO Buscar apenas ativos
	public void search() {
		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery(
				"SELECT e "
			  + "FROM Employee e "
			  + "WHERE upper(e.name) LIKE upper(:search)"
			  + "OR e.cpf LIKE :search");
		query.setParameter("search", "%"+ getSearch() + "%");
		query.setParameter("search", "%" + Util.maskCpf(search) + "%");
		listEmployee = query.getResultList();
	}

	public Sex[] getListSex() {
		return Sex.values();
	}
	
	public Situation[] getListSituation() {
		return Situation.values();
	}
	
	public boolean renderEmail() {
		if (userCreation == false)
			return true;
		else
			return false;
	}
	
	@Override
	public Employee getEntity() {
		if (entity == null)
			entity = new Employee();
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
	
	public List<Employee> getListEmployee() {
		if (listEmployee == null)
			listEmployee = new ArrayList<Employee>();
		return listEmployee;
	}
	
}