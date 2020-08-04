package br.unitins.acougue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class Product extends DefaultEntity<Product>{

	private static final long serialVersionUID = -5366950394656130455L;

	@Column(nullable = false, length = 150)
	private String name;
	
	@Column(length = 500)
	private String description;
	private int evaluation;
	
	@Column(nullable = false)
	private Double value;
	
	@Column(nullable = false)
	private String lot;

	@Temporal(TemporalType.DATE)
	private Date shelfLife;

	@Column(nullable = false)
	private Double availableQuantity;
	
	public Product() {
		super();
		this.value = 0.0;
	}
	
	public Product(String name, String description, int evaluation, String lot, Date shelfLife,
			Double availableQuantity) {
		super();
		this.name = name;
		this.description = description;
		this.evaluation = evaluation;
		this.lot = lot;
		this.shelfLife = shelfLife;
		this.availableQuantity = availableQuantity;
	}

	@Override
	public Validator<Product> getValidator() {
		return null;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}
	
	public Date getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(Date shelfLife) {
		this.shelfLife = shelfLife;
	}

	public Double getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Double availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
}