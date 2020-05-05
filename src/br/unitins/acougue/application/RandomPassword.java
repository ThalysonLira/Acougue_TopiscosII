package br.unitins.acougue.application;

import java.util.Random;

public class RandomPassword {

	private static final char[] UPPER_CHARACTERS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final char[] LOWER_CHARACTERS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final char[] NUMERIC_CHARACTERS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private static final char[] SPECIAL_CHARACTERS = { '!', '@', '#', '$', '%', '&', '=', '+', '?' };

	private static char chooseCharacters() {
		int c;
		Random r = new Random();
		
		switch (r.nextInt(4)) {
		case 0:
			c = new Random().nextInt(UPPER_CHARACTERS.length);
			return UPPER_CHARACTERS[c];
		case 1:
			c = new Random().nextInt(LOWER_CHARACTERS.length);
			return LOWER_CHARACTERS[c];
		case 2:
			c = new Random().nextInt(NUMERIC_CHARACTERS.length);
			return NUMERIC_CHARACTERS[c];
		case 3:
			c = new Random().nextInt(SPECIAL_CHARACTERS.length);
			return SPECIAL_CHARACTERS[c];
		}
		
		return '0';
	}

	public static String generatePassword(int n) {
		String password = "";
		
		for(int i = 0; i < n; i++) {
			password += chooseCharacters();
		}
		
		return password;
	}
}
