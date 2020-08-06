package br.unitins.acougue.model.validator;

import br.unitins.acougue.application.ValidateException;
import br.unitins.acougue.model.LegalClient;
import br.unitins.acougue.model.Person;
import br.unitins.acougue.model.PhysicalClient;
import br.unitins.acougue.repository.LegalClientRepository;
import br.unitins.acougue.repository.PhysicalClientRepository;

public class ClientValidator implements Validator<Person> {

	@Override
	public void validate(Person entity) throws ValidateException {
		if (entity.getClass() == PhysicalClient.class)
			validateCpf((PhysicalClient) entity);
		else
			validateCnpj((LegalClient) entity);
	}

	private void validateCpf(PhysicalClient entity) throws ValidateException {
		PhysicalClientRepository repository = new PhysicalClientRepository();
		if (repository.contains(entity.getId(), entity.getCpf())) {
			throw new ValidateException("Cpf Inválido. Este cpf já está sendo utilizado por outro cliente.");
		}
	}
	
	private void validateCnpj(LegalClient entity) throws ValidateException {
		LegalClientRepository repository = new LegalClientRepository();
		if (repository.contains(entity.getId(), entity.getCnpj())) {
			throw new ValidateException("Cnpj Inválido. Este cnpj já está sendo utilizado por outro cliente.");
		}
	}

}