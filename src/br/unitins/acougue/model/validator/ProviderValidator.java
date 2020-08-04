package br.unitins.acougue.model.validator;

import br.unitins.acougue.application.ValidateException;
import br.unitins.acougue.model.Person;
import br.unitins.acougue.model.Provider;
import br.unitins.acougue.repository.ProviderRepository;

public class ProviderValidator implements Validator<Person> {

	@Override
	public void validate(Person entity) throws ValidateException {
		validateCnpj((Provider) entity);
	}

	private void validateCnpj(Provider entity) throws ValidateException {
		ProviderRepository repository = new ProviderRepository();
		if (repository.contains(entity.getId(), entity.getCnpj())) {
			throw new ValidateException("Cnpj Inválido. Este Cnpj já está sendo utilizado por outro fornecedor.");
		}
	}
	
}