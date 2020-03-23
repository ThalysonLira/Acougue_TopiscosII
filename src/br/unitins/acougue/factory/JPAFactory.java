package br.unitins.acougue.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFactory {

	private JPAFactory() {

	}

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Academico");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
}