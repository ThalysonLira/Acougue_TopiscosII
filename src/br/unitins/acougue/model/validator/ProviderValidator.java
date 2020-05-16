package br.unitins.acougue.model.validator;

import br.unitins.acougue.application.ValidateException;
import br.unitins.acougue.model.Provider;
import br.unitins.acougue.repository.ProviderRepository;

public class ProviderValidator implements Validator<Provider> {

	@Override
	public void validate(Provider entity) throws ValidateException {
		validateCnpj(entity);
	}

	private void validateCnpj(Provider entity) throws ValidateException {
		ProviderRepository repository = new ProviderRepository();
		if (repository.contains(entity.getId(), entity.getCnpj())) {
			throw new ValidateException("Cnpj Inv�lido. Este Cnpj j� est� sendo utilizado por outro fornecedor.");
		}
	}
	
}