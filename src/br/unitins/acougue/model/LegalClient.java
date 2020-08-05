package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LegalClient extends Client {

	private static final long serialVersionUID = 6635432464296089619L;

	@Column(length = 18, unique = true)
	private String cnpj;
	
	@Column
	private String companyName;
	
	public LegalClient() {
		super(PersonType.JURIDICA);
	}

	public LegalClient(PersonType type) {
		super(type);
	}

	public LegalClient(String cnpj, String companyName) {
		super(PersonType.JURIDICA);
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