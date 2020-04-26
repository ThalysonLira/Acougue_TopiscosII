package br.unitins.acougue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Stock")
public class ItemStock extends DefaultEntity<ItemStock> {

	private static final long serialVersionUID = -4750642961165057477L;

	private Product product;
	private String name;
	private int evaluation;

	@Temporal(TemporalType.DATE)
	private Date shelfLife;

	@Column(nullable = false)
	private Double availableQuantity;

	public ItemStock() {
		super();
	}

	public Product getProduct() {
		if (product == null)
			product = new Product();
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getShelfLife() {
		return shelfLife;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
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