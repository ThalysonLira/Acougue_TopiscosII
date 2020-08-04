package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class Item extends DefaultEntity<Item> {

	private static final long serialVersionUID = -4750642961165057477L;

	@OneToOne @JoinColumn(nullable = false)
	private Product product;
	
	@Column(nullable = false)
	private Double quantity;

	@Column(nullable = false)
	private Double value;

	public Item() {
		super();
		this.quantity = 0.0;
		this.value = 0.0;
	}
	
	public Item(Product product) {
		super();
		this.product = product;
	}
	
	public Item(Product product, Double quantity, Double value) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.value = value;
	}

	@Override
	public Validator<Item> getValidator() {
		return null;
	}

	public Product getProduct() {
		if (product == null)
			product = new Product();
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getValue() {
		value = getProduct().getValue() * getQuantity();
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}