package br.unitins.acougue.model;

public enum Situation {
	INATIVO(0, "Inativo"),
	ATIVO(1, "Ativo");
	
	private int value;
	private String label;
	
	private Situation(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public static Situation valueOf(int value) {
		for (Situation situation : Situation.values()) {
			if (situation.getValue() == value) {
				return situation;
			}
		}
		return null;
	}
	
}