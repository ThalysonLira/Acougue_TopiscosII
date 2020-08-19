package br.unitins.acougue.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.LegalClient;
import br.unitins.acougue.model.Person;

public class LegalClientRepository extends Repository<Person> {

	public List<LegalClient> findByName(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE upper(c.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}
	
	public List<LegalClient> findByCnpj(String cnpj) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE c.cnpj ");
		jpql.append("LIKE :cnpj ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("cnpj", "%" + cnpj + "%");

		return query.getResultList();
	}
	
	public List<LegalClient> findByNameOrCnpj(String search) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE c.name ");
		jpql.append("LIKE :name ");
		jpql.append("OR c.cnpj ");
		jpql.append("LIKE :cnpj ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + search + "%");
		query.setParameter("cnpj", "%" + search + "%");

		return query.getResultList();
	}
	
	public boolean contains(Integer id, String search) {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT COUNT(*) ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE c.cnpj = ? ");
		jpql.append("AND c.id <> ? ");
		
		Query query = getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter(1, Util.maskCnpj(search));
		query.setParameter(2, id == null ? -1 : id);
		
		BigInteger result = (BigInteger) query.getSingleResult();
		return (result == null || result.equals(BigInteger.ZERO)) ? false : true;
	}

}