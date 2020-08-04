package br.unitins.acougue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import br.unitins.acougue.model.validator.UserValidator;
import br.unitins.acougue.model.validator.Validator;

@Entity(name = "UserSystem")
public class User extends DefaultEntity<User>{

	private static final long serialVersionUID = 6179265536423951695L;
	
	@Column(nullable = false, length = 100, unique = true)
	private String email;
	
	@Column(nullable = false, length = 150)
	private String password;
	
	@Column(nullable = false)
	private Profile profile;
	
	public User() {
		super();
	}
	
	@Override
	public Validator<User> getValidator() {
		return new UserValidator();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}