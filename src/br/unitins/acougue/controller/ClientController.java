package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.application.RandomPassword;
import br.unitins.acougue.application.RepositoryException;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.controller.listener.ClientListener;
import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.Client;
import br.unitins.acougue.model.Profile;
import br.unitins.acougue.model.Sex;
import br.unitins.acougue.model.User;
import br.unitins.acougue.repository.Repository;

@Named
@ViewScoped
public class ClientController extends Controller<Client> {

	private static final long serialVersionUID = -1443526557455688665L;

	private String search;
	private List<Client> listClient;
	private boolean userCreation;
	
	public void openClientListener() {
		ClientListener listener = new ClientListener();
		listener.open();
	}
	
	public void getClientListener(SelectEvent event) {
		Client entity = (Client) event.getObject();
		setEntity(entity);
	}

	// TODO refazer definição de email
	public void renderEmail() {
		if ((getEntity().getUser().getEmail() == null) || (getEntity().getUser().getEmail().isEmpty())) {
			userCreation = !userCreation;
		} else {
			userCreation = false;
		}
	}

	private void createUser() {
		if (getEntity().getUser().getId() == null) {
			// TODO encaminhar senha criada para email
			getEntity().getUser().setPassword(RandomPassword.generatePassword(8));
			getEntity().getUser().setCreationDate(new Date());
			getEntity().getUser().setProfile(Profile.CLIENTE);
		}
	}

	public boolean disableToggle() {
		if ((getEntity().getUser().getEmail() == null) || (getEntity().getUser().getEmail().isEmpty()))
			return false;
		else
			return true;
	}
	
	public Sex[] getListSex() {
		return Sex.values();
	}

	@Override
	public void save() {
		if ((getEntity().getUser().getEmail() != null) && (!getEntity().getUser().getEmail().isEmpty())) {
			Repository<User> ru = new Repository<User>();
			User user = getEntity().getUser();
			createUser();

			try {
				ru.beginTransaction();
				ru.save(user);
				getEntity().setUser(ru.refresh(user));
				ru.commitTransaction();

				super.save();
			} catch (RepositoryException e) {
				e.printStackTrace();
				ru.rollbackTransaction();
				Util.addMessageError("Erro ao salvar.");
			}
		} else {
			getEntity().setUser(null);
			super.save();
		}
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

	public List<Client> getListClient() {
		if (listClient == null)
			listClient = new ArrayList<Client>();
		return listClient;
	}

}