package br.unitins.acougue.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class Sale extends DefaultEntity<Sale>{

	private static final long serialVersionUID = 2834281686033039479L;

	@ManyToOne()
	@JoinColumn(nullable = false)
	private Client buyer;
	
	@OneToOne()
	private Employee salesman;
	
	@Column(nullable = false)
	private Double total;
	
	@Temporal(TemporalType.DATE)
	private Date saleDate;
	
	@Transient
	private List<Purchase> listPurchase;
	
	public Sale() {
		super();
		this.total = 0.0;
		this.saleDate = new Date();
	}
	
	@Override
	public Validator<Sale> getValidator() {
		return null;
	}

	public Client getBuyer() {
		return buyer;
	}

	public void setBuyer(Client buyer) {
		this.buyer = buyer;
	}

	public Employee getSalesman() {
		return salesman;
	}

	public void setSalesman(Employee salesman) {
		this.salesman = salesman;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public List<Purchase> getListPurchase() {
		if (listPurchase == null)
			listPurchase = new ArrayList<Purchase>();
		return listPurchase;
	}

	public void setListPurchase(List<Purchase> listPurchase) {
		this.listPurchase = listPurchase;
	}

}