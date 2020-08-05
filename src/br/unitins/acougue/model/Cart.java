package br.unitins.acougue.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.unitins.acougue.model.validator.Validator;

@Entity
public class Cart extends DefaultEntity<Cart> {

	private static final long serialVersionUID = 2392701376843797285L;

	@Column(nullable = false)
	private Double total;
	
	@JoinColumn(nullable = false)
	private Client client;

	@OneToOne @JoinColumn(nullable = false)
	private Sale sale;

	@JoinColumn(nullable = false)
	private List<Item> items;

	public Cart() {
		super();
		this.total = 0.0;
	}

	public Cart(Double total, Sale sale, List<Item> items) {
		super();
		this.total = total;
		this.sale = sale;
		this.items = items;
	}

	@Override
	public Validator<Cart> getValidator() {
		return null;
	}

	public Double getTotal() {
		if (!getItems().isEmpty()) {
			for (Item item : items) {
				total += item.getValue();
			}
		}
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Client getClient() {
		if (client == null)
			client = new Client();
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Sale getSale() {
		if (sale == null)
			sale = new Sale();
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public List<Item> getItems() {
		if (items == null)
			items = new ArrayList<Item>();
		return items;
	}

	public void setItems(List<Item> cart) {
		this.items = cart;
	}

}