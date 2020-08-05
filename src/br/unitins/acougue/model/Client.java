package br.unitins.acougue.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.unitins.acougue.model.validator.ClientValidator;
import br.unitins.acougue.model.validator.Validator;

@Entity @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Client extends Person {

	private static final long serialVersionUID = -6475130528537879079L;
	
	private PersonType type;
	
	public Client() {
		super();
	}
	
	public Client(PersonType type) {
		super();
		this.type = type;
	}
	
	public PersonType getType() {
		return type;
	}

	public void setType(PersonType type) {
		this.type = type;
	}

	@Override
	public Validator<Person> getValidator() {
		return new ClientValidator();
	}
	
}