package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.Employee;
import br.unitins.acougue.model.Person;

public class EmployeeRepository extends Repository<Person> {

	public List<Employee> findByName(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT e ");
		jpql.append("FROM Employee e ");
		jpql.append("WHERE upper(e.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}

	public List<Employee> findByCpf(String cpf) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT e ");
		jpql.append("FROM Employee e ");
		jpql.append("WHERE e.cpf ");
		jpql.append("LIKE :cpf ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("cpf", "%" + cpf + "%");

		return query.getResultList();
	}

	public List<Employee> findByNameOrCpf(String search) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT e ");
		jpql.append("FROM Employee e ");
		jpql.append("WHERE upper(e.name) ");
		jpql.append("LIKE upper(:search) ");
		jpql.append("OR e.cpf ");
		jpql.append("LIKE :search ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("search", "%" + search + "%");
		query.setParameter("search", "%" + search + "%");

		return query.getResultList();
	}
	
	public boolean contains(Integer id, String cpf) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT COUNT(*) ");
		jpql.append("FROM Employee e ");
		jpql.append("WHERE e.cpf = ? ");
		jpql.append("AND e.id <> ? ");
		
		Query query = getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter(1, Util.maskCpf(cpf));
		query.setParameter(2, id == null ? -1 : id);
		
		return (long) query.getSingleResult() == 0 ? false : true;
	}

}