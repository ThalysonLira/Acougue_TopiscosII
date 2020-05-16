package br.unitins.acougue.model.validator;

import java.time.LocalDate;

import br.unitins.acougue.application.ValidateException;
import br.unitins.acougue.model.Client;
import br.unitins.acougue.repository.ClientRepository;

public class ClientValidator implements Validator<Client> {

	@Override
	public void validate(Client entity) throws ValidateException {
		validateCpf(entity);
		validateBirthDate(entity);
	}

	private void validateCpf(Client entity) throws ValidateException {
		ClientRepository repository = new ClientRepository();
		if (repository.contains(entity.getId(), entity.getCpf())) {
			throw new ValidateException("Cpf Inválido. Este cpf já está sendo utilizado por outro cliente.");
		}
	}

	private void validateBirthDate(Client entity) throws ValidateException {
		LocalDate data = new java.sql.Date(entity.getBirthDate().getTime()).toLocalDate(); 
		LocalDate dataLimite = 	LocalDate.now().minusYears(10);

		if (data.isAfter(dataLimite)) {
			throw new ValidateException("Data Inválida. O cliente não pode ter menos de 10 anos.");
		}
	}

}