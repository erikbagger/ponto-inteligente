/*package br.com.udemy.erikbagger.pontointeligente.api.domain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	
	private Logger log = LoggerFactory.getLogger(PasswordUtils.class);

	public String generatePassword(String password) {
		if (password == null) {
			return password;
		}
		log.info("Gerando hash para a senha: {}", password);
		return new BCryptPasswordEncoder().encode(password);
	}
}
*/