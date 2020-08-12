package br.unitins.acougue.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.acougue.model.State;
import br.unitins.acougue.repository.StateRepository;

@FacesConverter(forClass = State.class)
public class StateConverter implements Converter<State>{

	@Override
	public State getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty())
			return null;
		StateRepository repo = new StateRepository();
		return repo.findById(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, State value) {
		if (value == null || value.getId() == null)
			return null;
		
		return value.getId().toString();
	}
	
}