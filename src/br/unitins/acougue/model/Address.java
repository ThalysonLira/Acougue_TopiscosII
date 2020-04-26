package br.unitins.acougue.model;

import javax.persistence.Column;

public class Address extends DefaultEntity<Address>{

	private static final long serialVersionUID = 205467836201825885L;
	
	@Column(nullable = false, length = 8)
	private String cep;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String number;
	
	@Column(length = 100)
	private String complement;
	
	@Column(nullable = false)
	private String neighborhood;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;

	public Address() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}