package br.unitins.acougue.model;

import java.util.ArrayList;
import java.util.List;

public enum Profile {
	CLIENTE(0, "Cliente"),
	FORNECEDOR(1, "Fornecedor"),
	FUNCIONARIO(2, "Funcionario"),
	VENDEDOR(3, "Vendedor"),
	GERENTE(4, "Gerente"),
	ADMINISTRADOR(5, "Administrador");
	
	private int value;
	private String label;
	private List<String> access = null;

	Profile(int value, String label) {
		this.value = value;
		this.label = label;
	}
	
	private void userType(int value, String label) {
		this.value = value;
		this.label = label;
		
		access = new ArrayList<String>();
		if (value == 1) {
			access.add("pages/aluno.xhtml");
			access.add("pages/alunolisting.xhtml");
			access.add("pages/professor.xhtml");
			access.add("pages/professorlisting.xhtml");
			access.add("pages/professorlistingsql.xhtml");
			access.add("pages/estado.xhtml");
			access.add("pages/estadolisting.xhtml");
			access.add("pages/cidade.xhtml");
			access.add("pages/cidadelisting.xhtml");
			access.add("pages/relatorioaluno.xhtml");
			access.add("img-professor");
		} else {
			access.add("pages/aluno.xhtml");
			access.add("pages/estado.xhtml");
			access.add("pages/cidade.xhtml");
		}
	}
	
	public static Profile valueOf(int value) {
		for (Profile profile : Profile.values()) {
			if (profile.getValue() == value) {
				return profile;
			}
		}
		return null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public List<String> getAccess() {
		return access;
	}

}