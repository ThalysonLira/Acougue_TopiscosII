package br.unitins.acougue.controller;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.unitins.acougue.application.RepositoryException;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.application.ValidateException;
import br.unitins.acougue.application.VersionException;
import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.DefaultEntity;
import br.unitins.acougue.repository.Repository;

public abstract class Controller<T extends DefaultEntity<? super T>> implements Serializable {

	private static final long serialVersionUID = 3135991960979657083L;

	protected T entity;

	public Controller() {
		super();
	}

	public void save() {
		if (safeSave()) {
			clear();
			Util.addMessageInfo("Cadastro realizado com sucesso.");
		}
	}
	
	protected boolean safeSave() {
		Repository<T> r = new Repository<T>();
		try {
			r.beginTransaction();
			setEntity(r.save(getEntity()));
			r.commitTransaction();	
		} catch (RepositoryException e) {
			e.printStackTrace();
			r.rollbackTransaction();
			Util.addMessageError("Erro ao salvar.");
			return false;
		} catch (VersionException e) {
			e.printStackTrace();
			r.rollbackTransaction();
			Util.addMessageError("Erro ao salvar. Por favor, atualize a página e faça o cadastro novamente.");
			return false;
		} catch (ValidateException e) {
			System.out.println(e.getMessage());
			r.rollbackTransaction();
			Util.addMessageError(e.getMessage());
			return false;
		}
		return true;
	}

	public void delete() {
		Repository<T> r = new Repository<T>();

		try {
			r.beginTransaction();
			r.delete(getEntity());
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