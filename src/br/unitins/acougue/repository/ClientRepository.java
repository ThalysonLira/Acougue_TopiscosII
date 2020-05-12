package br.unitins.acougue.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.Client;

public class ClientRepository extends Repository<Client> {

	public List<Client> findByName(String name) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE upper(c.name) ");
		jpql.append("LIKE upper(:name) ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("name", "%" + name + "%");

		return query.getResultList();
	}

	public List<Client> findByCpf(String cpf) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE c.cpf ");
		jpql.append("LIKE :cpf ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("cpf", "%" + Util.maskCpf(cpf) + "%");

		return query.getResultList();
	}

	public List<Client> findByNameOrCpf(String search) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT c ");
		jpql.append("FROM Client c ");
		jpql.append("WHERE upper(c.name) ");
		jpql.append("LIKE upper(:search) ");
		jpql.append("OR c.cpf ");
		jpql.append("LIKE :search ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("search", "%" + search + "%");
		query.setParameter("search", "%" + Util.maskCpf(search) + "%");

		return query.getResultList();
	}

}