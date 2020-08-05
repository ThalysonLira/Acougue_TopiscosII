package br.unitins.acougue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Meat extends Product {

	private static final long serialVersionUID = -8781574807821411262L;

	@Column(nullable = false)
	private String cut; 
	
	@Column(nullable = false)
	private String animal;
	
	@Column(nullable = false)
	private int category;
	
	@Column(nullable = false)
	private MeatType meatType;
	
	public Meat() {
		super(ProductType.CARNE);
		super.setValue(0.0);
	}

	public Meat(String name, String description, int evaluation, Double value, ProductType type, String lot,
			Date shelfLife, Double availableQuantity) {
		super(name, description, evaluation, value, type, lot, shelfLife, availableQuantity);
	}

	public Meat(String cut, String animal, int category, MeatType meatType, Double valueKG) {
		super();
		this.cut = cut;
		this.animal = animal;
		this.category = category;
		this.meatType = meatType;
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

	public MeatType getMeatType() {
		return meatType;
	}

	public void setMeatType(MeatType meatType) {
		this.meatType = meatType;
	}
	
}