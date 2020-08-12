package br.unitins.acougue.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.acougue.model.Profile;

@Converter(autoApply = true)
public class UserConverter implements AttributeConverter<Profile, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Profile profile) {
		return profile.getValue();
	}

	@Override
	public Profile convertToEntityAttribute(Integer value) {
		if (value == null) 
			return null;
		return Profile.valueOf(value);
	}
	
}