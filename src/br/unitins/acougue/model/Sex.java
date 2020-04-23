package br.unitins.acougue.model;

public enum Sex {
	FEMININO(0, "Feminino"),
	MASCULINO(1, "Masculino");

	private int value;
	private String label;

	private Sex(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static Sex valueOf(int value) {
		for (Sex sex : Sex.values()) {
			if (sex.getValue() == value) {
				return sex;
			}
		}
		return null;
	}
	
}