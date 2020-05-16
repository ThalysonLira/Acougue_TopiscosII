package br.unitins.acougue.model.validator;

import br.unitins.acougue.application.ValidateException;

public interface  Validator <T> {
	
	public void validate(T entity) throws ValidateException;

}