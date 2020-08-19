package br.unitins.acougue.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Person extends DefaultEntity<Person> {

	private static final long serialVersionUID = -8416462140759250308L;

	@OneToOne(cascade = CascadeType.ALL) @JoinColumn(unique = true)
	private User user;
	
	@Column(nullable = false)
	private String name;
	
	@JoinColumn
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL) @JoinColumn(unique = true)
	private Phone phone;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	public Person() {
		super();
	}

	public Person(User user, String name, Address address, Phone phone, Date birthDate) {
		super();
		this.user = user;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.birthDate = birthDate;
	}

	public User getUser() {
		if (user == null)
			user = new User();
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		if (address == null)
			address = new Address();
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Phone getPhone() {
		if (phone == null)
			phone = new Phone();
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}