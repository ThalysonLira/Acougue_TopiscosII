package br.unitins.acougue.application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.swing.text.MaskFormatter;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {

	// /images
	public static final String PATH_IMAGES = File.separator + "dev" + File.separator + "images";
	// /images/product
	public static final String PATH_IMAGES_PRODUCT = PATH_IMAGES + File.separator + "product";

	public static void main(String args[]) {
		System.out.println(System.getProperty("user.home") + PATH_IMAGES_PRODUCT);
	}

	public static void redirect(String page) {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		try {
			external.redirect(external.getRequestContextPath() + page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String maskCpf(String value) {
		MaskFormatter mask;
		try {
			if (value.length() < 4) {
				return value;
			} else if (value.length() < 7) {
				mask = new MaskFormatter("###.###");
			} else if (value.length() < 9) {
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
			} else if (value.length() < 9) {
				mask = new MaskFormatter("##.###.###");
			} else if (value.length() < 13) {
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
	
	public static boolean saveImageProduct(InputStream inputStream, String imageType, int idProduct) {
		// Exemplo da maquina do janio: /home/janio/images/professor
		String directory = System.getProperty("user.home") + PATH_IMAGES_PRODUCT;
		
		// Criando os diretorios caso nao exista
		File file = new File(directory);
		if (!file.exists()) {
			file.mkdirs(); // mkdirs - cria arquivo ou directory (caso o directory anterior nao exeista ele cria tambem)
		}
		
		try {
			// criando o espaco de memoria para armazenamento de uma imagem
			// inputStream - eh o fluxo de dados de entrada 
			BufferedImage bImage = ImageIO.read(inputStream);
			
			// estrutura final do arquivo ex: /home/janio/images/professor/01.png
			File finalFile = new File(directory + File.separator + idProduct + "." + imageType);
			// salvando a imagem
			// buffer da imagem, png/gif, 01.png
			ImageIO.write(bImage, imageType, finalFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static String encrypt(String value) {
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