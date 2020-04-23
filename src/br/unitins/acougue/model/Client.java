package br.unitins.acougue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Client extends DefaultEntity<Client>{

	private static final long serialVersionUID = -6475130528537879079L;
	
	private User user;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(nullable = false)
	private Sex sex;
	
	public Client() {
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
	
}