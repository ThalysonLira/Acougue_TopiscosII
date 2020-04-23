package br.unitins.acougue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Employee extends DefaultEntity<Employee>{

	private static final long serialVersionUID = 4721400599346259192L;

	private User user;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(nullable = false)
	private Sex sex;
	
	@Column(nullable = false)
	private Situation situation;
	
	public Employee() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Sex getSex() {
		return sex;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}
	
}