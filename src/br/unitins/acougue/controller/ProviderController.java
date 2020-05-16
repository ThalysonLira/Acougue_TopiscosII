package br.unitins.acougue.controller;

import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.application.RandomPassword;
import br.unitins.acougue.controller.listener.ProviderListener;
import br.unitins.acougue.model.Provider;
import br.unitins.acougue.model.Profile;
import br.unitins.acougue.model.Situation;
import br.unitins.acougue.model.User;

@Named
@ViewScoped
public class ProviderController extends Controller<Provider> {

	private static final long serialVersionUID = 3679724066632322580L;

	private String search;
	private List<Provider> listProvider;
	private boolean userCreation;

	public void openProviderListener() {
		ProviderListener listener = new ProviderListener();
		listener.open();
	}

	public void getProviderListener(SelectEvent event) {
		clear();
		Provider entity = (Provider) event.getObject();
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
		getEntity().getUser().setProfile(Profile.CLIENTE);
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
	public Provider getEntity() {
		if (entity == null) {
			entity = new Provider();
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