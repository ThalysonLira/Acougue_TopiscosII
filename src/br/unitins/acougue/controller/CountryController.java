package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.controller.listener.CountryListener;
import br.unitins.acougue.model.City;
import br.unitins.acougue.model.Country;
import br.unitins.acougue.model.State;

@Named @ViewScoped
public class CountryController extends Controller<Country> {

	private static final long serialVersionUID = -8118174248654594775L;
	
	private String search;
	private List<Country> listCountry;
	
	public void openCountryListener() {
		CountryListener listener = new CountryListener();
		listener.open();
	}
	
	public void getCountryListener(SelectEvent event) {
		clear();
		Country entity = (Country) event.getObject();
		setEntity(entity);
	}
	
	public void getCapitalListener(SelectEvent event) {
		City capital = (City) event.getObject();
		getEntity().setCapital(capital);
	}
	
	public void removeState(State state) {
		getEntity().getStates().remove(state);
	}
	
	@Override
	public Country getEntity() {
		if (entity == null)
			entity = new Country();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Country> getListCountry() {
		if (listCountry == null)
			listCountry = new ArrayList<Country>();
		return listCountry;
	}

}