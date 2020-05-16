package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.unitins.acougue.model.validator.ProviderValidator;
import br.unitins.acougue.model.validator.Validator;

@Entity
public class Provider extends DefaultEntity<Provider>{

	private static final long serialVersionUID = -308678649071787773L;
	
	@OneToOne()
	private User user;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, length = 20)
	private String cnpj;
	
	@Column(nullable = false)
	private Situation situation;
	
	public Provider() {
		super();
	}
	
	@Override
	public Validator<Provider> getValidator() {
		return new ProviderValidator();
	}

	public User getUser() {
		if (user == null)
			user = new User();
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

}