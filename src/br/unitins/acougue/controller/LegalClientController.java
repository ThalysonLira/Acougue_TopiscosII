package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.application.RandomPassword;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.controller.listener.LegalClientListener;
import br.unitins.acougue.model.LegalClient;
import br.unitins.acougue.model.Person;
import br.unitins.acougue.model.Profile;
import br.unitins.acougue.model.Sex;
import br.unitins.acougue.model.User;

@Named
@ViewScoped
public class LegalClientController extends Controller<Person> {

	private static final long serialVersionUID = -1443526557455688665L;

	private String search;
	private List<LegalClient> listLegalClient;
	private boolean userCreation;

	public void openClientListener() {
		LegalClientListener listener = new LegalClientListener();
		listener.open();
	}

	public void getLegalClientListener(SelectEvent event) {
		clear();

		if (event.getObject().getClass() != LegalClient.class)
			Util.addMessageError("Tipo de cliente inválido!");
		else {
			LegalClient entity = (LegalClient) event.getObject();
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
	public LegalClient getEntity() {
		if (entity == null) {
			entity = new LegalClient();
			entity.setUser(new User());
		}
		return (LegalClient) entity;
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

	public List<LegalClient> getListLegalClient() {
		if (listLegalClient == null)
			listLegalClient = new ArrayList<LegalClient>();
		return listLegalClient;
	}

}