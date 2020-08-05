package br.unitins.acougue.model;

public enum ProductType {
	CARNE(0, "Carne");

	private int value;
	private String label;

	private ProductType(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static ProductType valueOf(int value) {
		for (ProductType type : ProductType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}

}