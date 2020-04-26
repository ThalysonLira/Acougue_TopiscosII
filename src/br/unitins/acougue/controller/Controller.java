package br.unitins.acougue.controller;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.unitins.acougue.application.RepositoryException;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.DefaultEntity;
import br.unitins.acougue.repository.Repository;

public abstract class Controller<T extends DefaultEntity<T>> implements Serializable{

	private static final long serialVersionUID = 3135991960979657083L;
	
	protected T entity;
	
	public Controller() {
		super();
	}

	public void save() {
		Repository<T> r = new Repository<T>();
	
		try {
			r.beginTransaction();
			r.salvar(getEntity());
			r.commitTransaction();
		} catch (RepositoryException e) {
			e.printStackTrace();
			r.rollbackTransaction();
			Util.addMessageError("Erro ao salvar.");
			return;
		}
	
		this.clear();
		Util.addMessageInfo("Cadastro realizado com sucesso.");
	}

	public void delete() {
		Repository<T> r = new Repository<T>();
	
		try {
			r.beginTransaction();
			r.excluir(getEntity());
			r.commitTransaction();
		} catch (RepositoryException e) {
			e.printStackTrace();
			r.rollbackTransaction();
			Util.addMessageError("Erro ao excluir.");
			return;
		}
	
		this.clear();
		Util.addMessageInfo("Exclusão realizada com sucesso.");
	}
	
	public void edit(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((T) em.find(getEntity().getClass(), id));
	}
	
	public void clear() {
		setEntity(null);
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}

}