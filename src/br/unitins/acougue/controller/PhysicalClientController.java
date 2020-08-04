package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.application.RandomPassword;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.controller.listener.PhysicalClientListener;
import br.unitins.acougue.model.PhysicalClient;
import br.unitins.acougue.model.Person;
import br.unitins.acougue.model.Profile;
import br.unitins.acougue.model.Sex;
import br.unitins.acougue.model.User;

@Named
@ViewScoped
public class PhysicalClientController extends Controller<Person> {

	private static final long serialVersionUID = -1443526557455688665L;

	private String search;
	private List<PhysicalClient> listPhysicalClient;
	private boolean userCreation;

	public void openClientListener() {
		PhysicalClientListener listener = new PhysicalClientListener();
		listener.open();
	}

	public void getPhysicalClientListener(SelectEvent event) {
		clear();

		if (event.getObject().getClass() != PhysicalClient.class)
			Util.addMessageError("Tipo de cliente inválido!");
		else {
			PhysicalClient entity = (PhysicalClient) event.getObject();
			setEntity(entity);
			renderEmailField();
		}
	}

	public void renderEmailField() {
		if (getEntity().getUser() != null && getEntity().getUser().getId() != null)
			userCreation = true;
		else if (getEntity().getUser() != null && getEntity().getUser().getId() == null) {
			if (getEntity().getUser().getEmail() != null)
				userCreation = !userCreation;
		} else if (getEntity().getUser() == null)
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
		getEntity().getUser().setProfile(Profile.CLIENTE);
	}

	public Sex[] getListSex() {
		return Sex.values();
	}

	@Override
	public void save() {
		if (getEntity().getUser() != null && getEntity().getUser().getId() == null) {
			String email = getEntity().getUser().getEmail();
			if (email != null && !email.isEmpty())
				createNewUser();
			else
				getEntity().setUser(null);
		}

		// TODO descobrir porque não atualiza o USER
		super.save();
	}

	@Override
	public void clear() {
		super.clear();
		userCreation = false;
	}

	@Override
	public PhysicalClient getEntity() {
		if (entity == null) {
			entity = new PhysicalClient();
			entity.setUser(new User());
		}
		return (PhysicalClient) entity;
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

	public List<PhysicalClient> getListPhysicalClient() {
		if (listPhysicalClient == null)
			listPhysicalClient = new ArrayList<PhysicalClient>();
		return listPhysicalClient;
	}

}