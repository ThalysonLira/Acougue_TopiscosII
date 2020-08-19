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
import br.unitins.acougue.model.Employee;
import br.unitins.acougue.model.Person;
import br.unitins.acougue.repository.EmployeeRepository;

@Named @ViewScoped
public class EmployeeListener extends Listener<Person> {

	private static final long serialVersionUID = 3030358506307217188L;
	private List<Employee> list;
	private String search;
	
	public void search() {
		EmployeeRepository repository = new EmployeeRepository();
		setList(repository.findByNameOrCpf(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((Employee) em.find(Employee.class, id));
		
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

        PrimeFaces.current().dialog().openDynamic("/pages/listener/funcionario_lista", options, null);
	}

	@Override
	public Employee getEntity() {
		if (entity == null)
			entity = new Employee();
		return (Employee) entity;
	}

	public List<Employee> getList() {
		if (list == null)
			list = new ArrayList<Employee>();
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}