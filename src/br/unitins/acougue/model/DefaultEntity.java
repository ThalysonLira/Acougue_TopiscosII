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
import javax.persistence.Version;

import br.unitins.acougue.model.validator.Validator;

@MappedSuperclass // solucao em JPA para gerar a herança de uma entidade, mas manter como objeto
					// unico no bd
public abstract class DefaultEntity<T> implements Serializable {

	private static final long serialVersionUID = 3154775394284502047L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment do id no bd
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date changeDate;

	@Version
	private Integer version;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultEntity other = (DefaultEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
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