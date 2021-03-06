package br.unitins.acougue.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;

import br.unitins.acougue.application.RepositoryException;
import br.unitins.acougue.application.ValidateException;
import br.unitins.acougue.application.VersionException;
import br.unitins.acougue.factory.JPAFactory;
import br.unitins.acougue.model.DefaultEntity;

public class Repository<T extends DefaultEntity<? super T>> {

	private EntityManager entityManager;

	public Repository() {
		entityManager = JPAFactory.getEntityManager();
	}

	public Repository(EntityManager em) {
		entityManager = em;
	}

	public void beginTransaction() throws RepositoryException {
		try {
			getEntityManager().getTransaction().begin();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao iniciar uma transação.");
		}
	}

	public void commitTransaction() throws RepositoryException {
		try {
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao commitar uma transação.");
		}
	}
	
	public void rollbackTransaction() {
		try {
			getEntityManager().getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T save(T entity) throws RepositoryException, ValidateException, VersionException {
		try {
			if (entity.getValidator() != null)
				entity.getValidator().validate(entity);
			
			return getEntityManager().merge(entity);
		} catch (ValidateException e) {
			System.out.println(e.getMessage());
			throw e;
		
		} catch (OptimisticLockException e) {
			e.printStackTrace();
			throw new VersionException("Versão antiga. Erro de controle de concorrência.");
		} catch (Exception e) {
			System.out.println("Erro no repositório "
					+ "ao executar o método merge.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar.");
		} 
	}

	public void delete(T entity) throws RepositoryException {
		try {
			T obj = getEntityManager().merge(entity);
			getEntityManager().remove(obj);
		} catch (Exception e) {
			System.out.println("Erro ao relizar merge.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar uma transação.");
		}
	}
	
	public T refresh(T entity) throws RepositoryException {
		try {
			T obj = getEntityManager().merge(entity);
			getEntityManager().flush();
			getEntityManager().refresh(obj);
			return obj;
		} catch (Exception e) {
			System.out.println("Erro ao relizar merge.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar uma transação.");
		}
	}
	
	public T findById(Integer id) {
		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass(); 
		Class<T> theType = (Class<T>) (type).getActualTypeArguments()[0];
		T t = (T) getEntityManager().find(theType, id);
		return t;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}