package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class City extends DefaultEntity<City> {

	private static final long serialVersionUID = 4527236271431716255L;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne @JoinColumn(nullable = false)
	private State state;

	public City() {
		super();
	}

	public City(String name, State state) {
		super();
		this.name = name;
		this.state = state;
	}

	@Override
	public Validator<City> getValidator() {
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		if (state == null)
			state = new State();
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}