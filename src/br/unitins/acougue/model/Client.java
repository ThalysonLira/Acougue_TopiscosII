package br.unitins.acougue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.unitins.acougue.model.validator.ClientValidator;
import br.unitins.acougue.model.validator.Validator;

@Entity
public class Client extends DefaultEntity<Client> {

	private static final long serialVersionUID = -6475130528537879079L;
	
	@OneToOne()
	private User user;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, length = 14, unique = true)
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(nullable = false)
	private Sex sex;
	
	public Client() {
		super();
	}
	
	@Override
	public Validator<Client> getValidator() {
		return new ClientValidator();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		if (user == null)
			user = new User();
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