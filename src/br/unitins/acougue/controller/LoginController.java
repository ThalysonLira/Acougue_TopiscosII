package br.unitins.acougue.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.User;

@Named
@RequestScoped
public class LoginController {

	private String email;
	private String password;

	public void login() {
		// TODO Consultar BD
//		EntityManager em = JPAFactory.getEntityManager();
//		Query query = em.createQuery("SELECT u " + "FROM User u " + "WHERE u.email LIKE :email");
//		query.setParameter("email", "%" + getEmail() + "%");
//		User user = (User) query.getSingleResult();
//		
//		if (user.getPassword().equals(getPassword())) {
//			System.out.println("Entrou");
//		} else {
//			System.out.println("Erro");
//		}
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