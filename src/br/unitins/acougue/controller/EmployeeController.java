package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.application.RandomPassword;
import br.unitins.acougue.controller.listener.EmployeeListener;
import br.unitins.acougue.model.Employee;
import br.unitins.acougue.model.Profile;
import br.unitins.acougue.model.Sex;
import br.unitins.acougue.model.Situation;
import br.unitins.acougue.model.User;

@Named
@ViewScoped
public class EmployeeController extends Controller<Employee> {

	private static final long serialVersionUID = 1397513922277282373L;

	private String search;
	private List<Employee> listEmployee;
	private boolean userCreation;
	
	// TODO Buscar apenas ativos
	public void openEmployeeListener() {
		EmployeeListener listener = new EmployeeListener();
		listener.open();
	}
	
	public void getEmployeeListener(SelectEvent event) {
		clear();
		Employee entity = (Employee) event.getObject();
		setEntity(entity);
		renderEmailField();
	}
	
	public void renderEmailField() {
		if (getEntity().getUser() != null && getEntity().getUser().getId() != null)
			userCreation = true;
		else if (getEntity().getUser() != null && getEntity().getUser().getId() == null) {
			if (getEntity().getUser().getEmail() != null)
				userCreation = !userCreation;
		}
		else if (getEntity().getUser() == null)
			userCreation = false;
	}

	public boolean disableToggle() {
		if (getEntity().getUser() != null && getEntity().getUser().getId() != null) {
			userCreation = true;
			return true;
		}
		return false;
	}

	private void createNewUser() {
		// TODO encaminhar senha criada para email
		getEntity().getUser().setPassword(RandomPassword.generatePassword(8));
		getEntity().getUser().setCreationDate(new Date());
		getEntity().getUser().setProfile(Profile.FUNCIONARIO);
	}
	
	public Sex[] getListSex() {
		return Sex.values();
	}
	
	public Situation[] getListSituation() {
		return Situation.values();
	}

	@Override
	public void save() {
		if (getEntity().getUser() != null && getEntity().getUser().getId() == null) {
			String email = getEntity().getUser().getEmail();
			if (!email.isBlank() && !email.isEmpty())
				createNewUser();
			else
				getEntity().setUser(null);
		}
		
		// TODO descobrir porque não atualiza o USER
		super.save();
	}
	
	@Override
	public Employee getEntity() {
		if (entity == null) {
			entity = new Employee();
			entity.setUser(new User());
		}
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