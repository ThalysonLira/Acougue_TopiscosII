package br.unitins.acougue.model;

public enum Profile {
	CLIENTE(0, "Cliente"),
	FORNECEDOR(1, "Fornecedor"),
	FUNCIONARIO(2, "Funcionario"),
	VENDEDOR(3, "Vendedor"),
	GERENTE(4, "Gerente"),
	ADMINISTRADOR(5, "Administrador");
	
	private int value;
	private String label;

	Profile(int value, String label) {
		this.value = value;
		this.label = label;
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
	
	public static Profile valueOf(int value) {
		for (Profile profile : Profile.values()) {
			if (profile.getValue() == value) {
				return profile;
			}
		}
		return null;
	}
	
}