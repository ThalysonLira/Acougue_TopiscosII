package br.unitins.acougue.model;

import javax.persistence.Column;

public class ItemStock extends DefaultEntity<ItemStock>{

	private static final long serialVersionUID = -4750642961165057477L;
	
	@Column(nullable = false)
	private Product product;
	
	@Column(nullable = false)
	private Double availableQuantity;
	
	public ItemStock() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Double availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
}