package br.unitins.acougue.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class Country extends DefaultEntity<Country>{

	private static final long serialVersionUID = 8355604360845927828L;
	
	@Column(nullable = false)
	private String name;
	
	@JoinColumn(nullable = false)
	private City capital;
	
	@OneToMany(mappedBy = "country")
	private List<State> states;
	
	public Country() {
		super();
	}
	
	public Country(String name, City capital, List<State> states) {
		super();
		this.name = name;
		this.capital = capital;
		this.states = states;
	}

	@Override
	public Validator<Country> getValidator() {
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCapital() {
		if (capital == null)
			capital = new City();
		return capital;
	}

	public void setCapital(City capital) {
		this.capital = capital;
	}

	public List<State> getStates() {
		if (states == null)
			states = new ArrayList<State>();
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
}