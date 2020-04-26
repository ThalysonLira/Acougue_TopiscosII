package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;

//@Entity
public class ItemSale extends DefaultEntity<ItemSale>{

	private static final long serialVersionUID = 2392701376843797285L;
	
	@Column(nullable = false)
	private ItemStock item;
	
	@Column(nullable = false)
	private Double value;
	
	@Column(nullable = false)
	private Sale sale;

	public ItemSale() {
		super();
	}

	public ItemStock getItem() {
		return item;
	}

	public void setItem(ItemStock item) {
		this.item = item;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
}