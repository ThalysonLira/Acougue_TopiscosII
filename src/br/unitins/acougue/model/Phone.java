package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class Phone extends DefaultEntity<Phone> {

	private static final long serialVersionUID = -5896182273654357753L;

	@Column(nullable = false)
	private String areaCode;
	
	@Column(nullable = false)
	private String number;
	
	@Override
	public Validator<Phone> getValidator() {
		return null;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}