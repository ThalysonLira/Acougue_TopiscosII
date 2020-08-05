package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class PhysicalPerson extends Person {
	
	private static final long serialVersionUID = -936637378274192583L;

	@Column(nullable = false, length = 14, unique = true)
	private String cpf;
	
	@Column(nullable = false)
	private Sex sex;

	public PhysicalPerson() {
		super();
		this.cpf = "";
	}

	public PhysicalPerson(String cpf, Sex sex) {
		super();
		this.cpf = cpf;
		this.sex = sex;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
}