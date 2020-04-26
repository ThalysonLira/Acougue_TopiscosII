package br.unitins.acougue.application;

import java.text.ParseException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.text.MaskFormatter;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {

	public static void main(String args[]) {
		System.out.println("Hello");
		System.out.println(maskCnpj("999"));
	}
	
	public static String maskCpf(String value) {
		MaskFormatter mask;
		try {
			
		if (value.length() < 4) {
			return value;
		} else if (value.length() < 7) {
			mask = new MaskFormatter("###.###");
		} else if (value.length() < 9){
			mask = new MaskFormatter("###.###.###");
		} else {
			mask = new MaskFormatter("###.###.###-##");
		}
		
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value).trim();
		} catch (ParseException e) {
			return value;
		}
	}
	
	public static String maskCnpj(String value) {
		MaskFormatter mask;
		try {
			
		if (value.length() < 3) {
			return value;
		} else if (value.length() < 6) {
			mask = new MaskFormatter("##.###");
		} else if (value.length() < 9){
			mask = new MaskFormatter("##.###.###");
		} else if (value.length() < 13){
			mask = new MaskFormatter("##.###.###/####");
		} else {
			mask = new MaskFormatter("##.###.###/####-##");
		}
		
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value).trim();
		} catch (ParseException e) {
			return value;
		}
	}

	public static String hashSHA256(String value) {
		return DigestUtils.sha256Hex(value);
	}

	public static void addMessageInfo(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public static void addMessageWarn(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
	}

	public static void addMessageError(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

}