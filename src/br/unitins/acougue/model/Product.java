package br.unitins.acougue.model;

public class Product extends DefaultEntity<Product>{

	private static final long serialVersionUID = -5366950394656130455L;

	private String animal;
	private String cut;
	private int category;
	private Double valueKG;
	
	public Product() {
		super();
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getCut() {
		return cut;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Double getValueKG() {
		return valueKG;
	}

	public void setValueKG(Double valueKG) {
		this.valueKG = valueKG;
	}
	
}