package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.unitins.acougue.model.validator.ProviderValidator;
import br.unitins.acougue.model.validator.Validator;

@Entity
public class Provider extends LegalPerson {

	private static final long serialVersionUID = -308678649071787773L;
	
	@Column(nullable = false)
	private Situation situation;
	
	public Provider() {
		super();
	}
	
	public Provider(String cnpj, String companyName) {
		super(cnpj, companyName);
	}

	@Override
	public Validator<Person> getValidator() {
		return new ProviderValidator();
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

}