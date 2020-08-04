package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity @DiscriminatorValue("Physical Client")
public class PhysicalClient extends Client {

	private static final long serialVersionUID = 2894663712410062862L;

	@Column(length = 14, unique = true)
	private String cpf;
	
	@Column
	private Sex sex;

	public PhysicalClient() {
		super(PersonType.FISICA);
	}

	public PhysicalClient(PersonType type) {
		super(type);
	}
	
	public PhysicalClient(String cpf, Sex sex) {
		super(PersonType.FISICA);
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