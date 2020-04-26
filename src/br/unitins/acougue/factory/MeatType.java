package br.unitins.acougue.factory;

public enum MeatType {
	BRANCA(0, "Branca"),
	VERMELHA(1, "Vermelha");

	private int value;
	private String label;

	private MeatType(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static MeatType valueOf(int value) {
		for (MeatType type : MeatType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
