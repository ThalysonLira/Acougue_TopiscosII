package br.unitins.acougue.model;

import java.util.ArrayList;
import java.util.List;

public enum Profile {
	CLIENTE(0, "Cliente"), FORNECEDOR(1, "Fornecedor"), VENDEDOR(2, "Vendedor"), FUNCIONARIO(3, "Funcionario"),
	GERENTE(4, "Gerente"), ADMINISTRADOR(5, "Administrador");

	private int value;
	private String label;
	private List<String> access = null;
	
	private Profile(int value, String label) {
		this.value = value;
		this.label = label;

		access = new ArrayList<String>();
		if (value == 1) {
			access.add("http://localhost:8080/Acougue_TopicosII/faces/pages/produto_lista.xhtml");
		} else if (value == 2) {
			access.add("pages/cliente_fisico.xhtml");
			access.add("pages/cliente_juridico.xhtml");
			access.add("pages/venda.xhtml");
			access.add("pages/usuario_lista.xhtml");
			access.add("pages/venda_lista.xhtml");

		} else if (value == 3) {
			access.add("pages/produto_carne.xhtml");
			access.add("pages/fornecedor.xhtml");
			access.add("pages/cidade.xhtml");
			access.add("pages/pais.xhtml");
			access.add("pages/carrinho_lista.xhtml");
			access.add("pages/cidade_lista.xhtml");
			access.add("pages/estado_lista.xhtml");
			access.add("pages/fornecedor_lista.xhtml");
			access.add("pages/pais_lista.xhtml");
		} else {
			access.add("pages/funcionario_lista.xhtml");
			access.add("pages/funcionario.xhtml");
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