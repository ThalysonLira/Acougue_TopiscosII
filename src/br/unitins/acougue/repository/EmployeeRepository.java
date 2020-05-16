package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.Employee;

public class EmployeeRepository extends Repository<Employee> {

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
		query.setParameter("cpf", "%" + Util.maskCpf(cpf) + "%");

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
		query.setParameter("search", "%" + Util.maskCpf(search) + "%");

		return query.getResultList();
	}
	
	public boolean contains(Integer id, String cpf) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT COUNT(*) ");
		jpql.append("FROM Employee e ");
		jpql.append("WHERE e.cpf = ? ");
		jpql.append("AND e.id <> ? ");
		
		Query query = getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter(1, cpf);
		query.setParameter(2, id == null ? -1 : id);
		
		return (long) query.getSingleResult() == 0 ? false : true;
	}

}