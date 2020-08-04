package br.unitins.acougue.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.unitins.acougue.model.validator.Validator;

@MappedSuperclass // solucao em JPA para gerar a herança de uma entidade, mas manter como objeto unico no bd
public abstract class DefaultEntity<T> implements Serializable {
	
	private static final long serialVersionUID = 3154775394284502047L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment do id no bd
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date changeDate;
	
	public abstract Validator<T> getValidator();

	@PrePersist
	private void updateDataBeforeInsert() {
		this.registrationDate = new Date();
		this.changeDate = this.registrationDate;
	}
		
	@PreUpdate
	private void updateDataBeforeUpdate() {
		this.changeDate = new Date();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
}