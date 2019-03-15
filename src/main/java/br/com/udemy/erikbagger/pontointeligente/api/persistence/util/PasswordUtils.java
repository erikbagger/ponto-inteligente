package br.com.udemy.erikbagger.pontointeligente.api.persistence.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	
	public static String generatePassword(String password) {
		if (password == null) {
			return password;
		}
		return new BCryptPasswordEncoder().encode(password);
	}
}
