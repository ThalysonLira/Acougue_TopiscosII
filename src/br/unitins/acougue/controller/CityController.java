package br.unitins.acougue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.controller.listener.CityListener;
import br.unitins.acougue.model.City;
import br.unitins.acougue.model.State;

@Named @ViewScoped
public class CityController extends Controller<City> {

	private static final long serialVersionUID = -8118174248654594775L;
	
	private String search;
	private List<City> listCity;
	
	public void openCityListener() {
		CityListener listener = new CityListener();
		listener.open();
	}
	
	public void getCityListener(SelectEvent event) {
		clear();
		City entity = (City) event.getObject();
		setEntity(entity);
	}
	
	public void getStateListener(SelectEvent event) {
		State state = (State) event.getObject();
		getEntity().setState(state);
	}
	
	@Override
	public City getEntity() {
		if (entity == null) {
			entity = new City();
			entity.setState(new State());
		}
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<City> getListCity() {
		if (listCity == null)
			listCity = new ArrayList<City>();
		return listCity;
	}

}