package br.unitins.acougue.model.validator;

import java.time.LocalDate;

import br.unitins.acougue.application.ValidateException;
import br.unitins.acougue.model.Employee;
import br.unitins.acougue.repository.EmployeeRepository;

public class EmployeeValidator implements Validator<Employee> {

	@Override
	public void validate(Employee entity) throws ValidateException {
		validateCpf(entity);
		validateBirthDate(entity);
	}
	
	private void validateCpf(Employee entity) throws ValidateException {
		EmployeeRepository repository = new EmployeeRepository();
		if (repository.contains(entity.getId(), entity.getCpf())) {
			throw new ValidateException("Cpf Inv�lido. Este cpf j� est� sendo utilizado por outro funcion�rio.");
		}
	}

	private void validateBirthDate(Employee entity) throws ValidateException {
		LocalDate data = new java.sql.Date(entity.getBirthDate().getTime()).toLocalDate(); 
		LocalDate dataLimite = 	LocalDate.now().minusYears(14);

		if (data.isAfter(dataLimite)) {
			throw new ValidateException("Data Inv�lida. O funcion�rio n�o pode ter menos de 14 anos.");
		}
	}

}