package br.unitins.acougue.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAFactory {

	private JPAFactory() {

	}

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Acougue_TopicosII");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void main(String args[]) {
//		EntityManagerFactory emf = 
//				Persistence.createEntityManagerFactory("Vestter");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select u FROM User u");
		query.getResultList();
		System.out.println("done.");
	}
	
}