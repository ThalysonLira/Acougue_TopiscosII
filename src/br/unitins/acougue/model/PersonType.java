package br.unitins.acougue.model;

public enum PersonType {
	FISICA(0, "Física"),
	JURIDICA(1, "Jurídica");

	private int value;
	private String label;

	private PersonType(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static PersonType valueOf(int value) {
		for (PersonType type : PersonType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}

}