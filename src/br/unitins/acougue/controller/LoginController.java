package br.unitins.acougue.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.acougue.application.Session;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.User;
import br.unitins.acougue.repository.UserRepository;

@Named @RequestScoped
public class LoginController {

	private String email;
	private String password;

	public String login() {
		UserRepository repository = new UserRepository();
		User user = (User) repository.validateLogin(email, password);
		
		if (user != null) {
			Session.getInstance().setAttribute("logUser", user);
			return "/index.xhtml?faces-redirect=true";
		}
		
		Util.addMessageError("Login ou Senha inválido.");
		return "";
	}
	
	public void forgotPassword() {
		// TODO implementar
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

}