package br.unitins.acougue.model;

import javax.persistence.Column;

public class Provider extends DefaultEntity<Provider>{

	private static final long serialVersionUID = -308678649071787773L;
	
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

	public User getUser() {
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