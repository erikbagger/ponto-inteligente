package br.com.udemy.erikbagger.pontointeligente.api.persistence.util;

import java.util.UUID;

public class PasswordUtils {
	
	public static String generatePassword(String password) {
		if (password == null) {
			return password;
		}
		return UUID.randomUUID().toString();
	}
}
