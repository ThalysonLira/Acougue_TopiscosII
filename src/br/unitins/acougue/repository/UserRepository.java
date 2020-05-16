package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.model.User;

public class UserRepository extends Repository<User> {
	
	public List<User> findByEmail(String email) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT u ");
		jpql.append("FROM UserSystem u ");
		jpql.append("WHERE upper(u.email) ");
		jpql.append("LIKE upper(:email) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("email", "%" + email + "%");

		return query.getResultList();
	}

	public List<User> findByProfile(String profile) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT u ");
		jpql.append("FROM UserSystem u ");
		jpql.append("WHERE u.profile ");
		jpql.append("LIKE :profile ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("profile", "%" + profile + "%");

		return query.getResultList();
	}
	
	public boolean contains(Integer id, String email) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT COUNT(*) ");
		jpql.append("FROM UserSystem u ");
		jpql.append("WHERE upper(u.email) = ? ");
		jpql.append("AND u.id <> ? ");
		
		Query query = getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter(1, email);
		query.setParameter(2, id == null ? -1 : id);
		
		return (long) query.getSingleResult() == 0 ? false : true;
	}
	
}