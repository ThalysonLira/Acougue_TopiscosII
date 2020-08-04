package br.unitins.acougue.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class Sale extends DefaultEntity<Sale>{

	private static final long serialVersionUID = 2834281686033039479L;

	@ManyToOne @JoinColumn
	private Employee salesman;
	
	@Temporal(TemporalType.DATE)
	private Date saleDate;
	
	@OneToOne @JoinColumn
	private Cart cart;
	
	public Sale() {
		super();
		this.saleDate = new Date();
	}
	
	@Override
	public Validator<Sale> getValidator() {
		return null;
	}

	public Employee getSalesman() {
		return salesman;
	}

	public void setSalesman(Employee salesman) {
		this.salesman = salesman;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
}