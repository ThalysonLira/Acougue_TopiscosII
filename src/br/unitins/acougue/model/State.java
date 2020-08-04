package br.unitins.acougue.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class State extends DefaultEntity<State> {

	private static final long serialVersionUID = 7429685493015550570L;
	
	@Column(nullable = false)
	private String name;
	
	@JoinColumn(nullable = false)
	private City capital;
	
	@ManyToOne @JoinColumn(nullable = false)
	private Country country;
	
	@OneToMany(mappedBy = "state")
	private List<City> cities;
	
	public State() {
		super();
	}

	public State(String name, City capital, Country country, List<City> cities) {
		super();
		this.name = name;
		this.capital = capital;
		this.country = country;
		this.cities = cities;
	}

	@Override
	public Validator<State> getValidator() {
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

	public Country getCountry() {
		if (country == null)
			country = new Country();
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<City> getCities() {
		if (cities == null)
			cities = new ArrayList<City>();
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}