package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class LegalPerson extends Person {

	private static final long serialVersionUID = -1951903267796370355L;
	
	@Column(nullable = false, length = 18, unique = true)
	private String cnpj;
	
	@Column(nullable = false)
	private String companyName;
	
	public LegalPerson() {
		super();
	}

	public LegalPerson(String cnpj, String companyName) {
		super();
		this.cnpj = cnpj;
		this.companyName = companyName;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}