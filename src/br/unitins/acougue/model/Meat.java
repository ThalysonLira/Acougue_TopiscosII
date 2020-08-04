package br.unitins.acougue.model;

import javax.persistence.Column;

public class Meat extends Product {

	private static final long serialVersionUID = -8781574807821411262L;

	@Column(nullable = false)
	private String cut; 
	
	@Column(nullable = false)
	private String animal;
	
	@Column(nullable = false)
	private int category;
	
	@Column(nullable = false)
	private MeatType type;
	
	public Meat() {
		super();
		super.setValue(0.0);
	}
	
	public Meat(String cut, String animal, int category, MeatType type, Double valueKG) {
		super();
		this.cut = cut;
		this.animal = animal;
		this.category = category;
		this.type = type;
		super.setValue(valueKG);
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
	
}