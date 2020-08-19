package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import br.unitins.acougue.application.RandomPassword;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.controller.listener.LegalClientListener;
import br.unitins.acougue.model.City;
import br.unitins.acougue.model.LegalClient;
import br.unitins.acougue.model.Person;
import br.unitins.acougue.model.Phone;
import br.unitins.acougue.model.Profile;
import br.unitins.acougue.model.Sex;
import br.unitins.acougue.model.State;
import br.unitins.acougue.model.User;
import br.unitins.acougue.repository.CityRepository;
import br.unitins.acougue.repository.StateRepository;

@Named @ViewScoped
public class LegalClientController extends Controller<Person> {

	private static final long serialVersionUID = -1443526557455688665L;

	private String search, state;
	private List<LegalClient> listLegalClient;
	private Phone phone;
	private boolean skip;


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
		}
	}

	private void createNewUser() {
		// TODO encaminhar senha criada para email
		getEntity().getUser().setPassword(RandomPassword.generatePassword(8));
		getEntity().getUser().setProfile(Profile.CLIENTE);
	}

	public Sex[] getListSex() {
		return Sex.values();
	}
	
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false;
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}
	
	public List<String> getStateListener() {
		StateRepository repository = new StateRepository();
		List<String> list = new ArrayList<String>();
		for (State state : repository.findByName("")) {
			list.add(state.getName());
		}
		return list;
	}

	// TODO Corrigir Erro de validação: o valor não é válido
	public List<City> getCityListener() {
		CityRepository repository = new CityRepository();
		return repository.findByState(getState());
	}

//	public void savePhone() {
//		if (!getEntity().getPhones().contains(getPhone()))
//			getEntity().getPhones().add(phone);
//		setPhone(null);
//	}
//
//	public void editPhone(Phone phone) {
//		setPhone(phone);
//	}
//
//	public void removePhone(Phone phone) {
//		getEntity().getPhones().remove(phone);
//		setPhone(null);
//	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Phone getPhone() {
		if (phone == null)
			phone = new Phone();
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<LegalClient> getListLegalClient() {
		if (listLegalClient == null)
			listLegalClient = new ArrayList<LegalClient>();
		return listLegalClient;
	}

}