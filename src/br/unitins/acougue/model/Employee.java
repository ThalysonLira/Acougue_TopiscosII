package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.unitins.acougue.model.validator.EmployeeValidator;
import br.unitins.acougue.model.validator.Validator;

@Entity
public class Employee extends PhysicalPerson {

	private static final long serialVersionUID = 4721400599346259192L;

	@Column
	private String registry;
	
	@Column(nullable = false)
	private Situation situation;
	
	public Employee() {
		super();
	}

	public Employee(String cpf, Sex sex) {
		super(cpf, sex);
	}
	
	@Override
	public Validator<Person> getValidator() {
		return new EmployeeValidator();
	}

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

}