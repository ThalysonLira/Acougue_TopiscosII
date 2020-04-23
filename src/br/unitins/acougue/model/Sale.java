package br.unitins.acougue.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Sale extends DefaultEntity<Sale>{

	private static final long serialVersionUID = 2834281686033039479L;

	@Column(nullable = false)
	private User buyer;
	private User salesman;
	
	@Column(nullable = false)
	private Double total;
	
	@Temporal(TemporalType.DATE)
	private Date saleDate;
	
	@Column(nullable = false)
	private List<ItemSale> listSale;
	
	public Sale() {
		super();
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public User getSalesman() {
		return salesman;
	}

	public void setSalesman(User salesman) {
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

	public List<ItemSale> getListSale() {
		return listSale;
	}

	public void setListSale(List<ItemSale> listSale) {
		this.listSale = listSale;
	}
	
}