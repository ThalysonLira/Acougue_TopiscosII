package br.unitins.acougue.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends DefaultEntity<Person> {

	private static final long serialVersionUID = -8416462140759250308L;

	@OneToOne
	private User user;
	
	@Column(nullable = false)
	private String name;
	
	@JoinColumn
	private Address address;
	
	@JoinColumn
	private List<Phone> phones;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	public Person() {
		super();
	}

	public Person(User user, String name, Address address, List<Phone> phones, Date birthDate) {
		super();
		this.user = user;
		this.name = name;
		this.address = address;
		this.phones = phones;
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

	public List<Phone> getPhones() {
		if (phones == null)
			phones = new ArrayList<Phone>();
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}