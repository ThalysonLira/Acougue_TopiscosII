package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class Product extends DefaultEntity<Product>{

	private static final long serialVersionUID = -5366950394656130455L;

	@Column(nullable = false)
	private String cut;
	
	@Column(nullable = false)
	private String animal;
	
	@Column(nullable = false)
	private int category;
	
	@Column(nullable = false)
	private MeatType type;
	
	@Column(nullable = false)
	private Double valueKG;
	
	@Column(length = 150)
	private String description;
	
	public Product() {
		super();
	}
	
	@Override
	public Validator<Product> getValidator() {
		return null;
	}

	public String getCut() {
		return cut;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}
	
	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public MeatType getType() {
		return type;
	}

	public void setType(MeatType type) {
		this.type = type;
	}

	public Double getValueKG() {
		return valueKG;
	}

	public void setValueKG(Double valueKG) {
		this.valueKG = valueKG;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}