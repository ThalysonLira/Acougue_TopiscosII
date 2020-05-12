package br.unitins.acougue.controller.listener;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.primefaces.PrimeFaces;

import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.DefaultEntity;

public abstract class Listener<T extends DefaultEntity<T>> implements Serializable {

	private static final long serialVersionUID = -7031379073645134419L;
	
	protected T entity;

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((T) em.find(getEntity().getClass(), id));
		PrimeFaces.current().dialog().closeDynamic(getEntity());
	}
	
}