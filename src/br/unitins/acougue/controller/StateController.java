package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.controller.listener.StateListener;
import br.unitins.acougue.model.City;
import br.unitins.acougue.model.Country;
import br.unitins.acougue.model.State;

@Named @ViewScoped
public class StateController extends Controller<State> {

	private static final long serialVersionUID = -8118174248654594775L;
	
	private String search;
	private List<State> listState;
	
	public void openStateListener() {
		StateListener listener = new StateListener();
		listener.open();
	}
	
	public void getStateListener(SelectEvent event) {
		clear();
		State entity = (State) event.getObject();
		setEntity(entity);
	}
	
	public void getCapitalListener(SelectEvent event) {
		City city = (City) event.getObject();
		getEntity().setCapital(city);
	}
	
	public void getCountryListener(SelectEvent event) {
		Country country = (Country) event.getObject();
		getEntity().setCountry(country);
	}
	
	@Override
	public State getEntity() {
		if (entity == null) {
			entity = new State();
			entity.setCapital(new City());
			entity.setCountry(new Country());
		}
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<State> getListState() {
		if (listState == null)
			listState = new ArrayList<State>();
		return listState;
	}

}