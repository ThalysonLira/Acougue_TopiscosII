package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class Purchase extends DefaultEntity<Purchase>{

	private static final long serialVersionUID = 2392701376843797285L;
	
	@ManyToOne()
	@JoinColumn(nullable = false)
	private ItemStock item;
	
	@Column(nullable = false)
	private Double value;
	private Double quantity;
	
	@OneToOne()
	@JoinColumn(nullable = false)
	private Sale sale;

	public Purchase() {
		super();
		this.quantity = 0.0;
	}
	
	@Override
	public Validator<Purchase> getValidator() {
		return null;
	}

	public ItemStock getItem() {
		if (item == null)
			item = new ItemStock();
		return item;
	}

	public void setItem(ItemStock item) {
		this.item = item;
	}

	public Double getValue() {
		value = getItem().getProduct().getValueKG();
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

}