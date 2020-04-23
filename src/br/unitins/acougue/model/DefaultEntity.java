package br.unitins.acougue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // solucao em JPA para gerar a herança de uma entidade, mas manter como objeto unico no bd
public class DefaultEntity<T> implements Serializable {
	
	private static final long serialVersionUID = 3154775394284502047L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment do id no bd
	private Integer id;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}