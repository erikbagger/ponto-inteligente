package br.com.udemy.erikbagger.pontointeligente.api.domain.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends Exception{

	private static final long serialVersionUID = -2939655698084299553L;
	
	private Errors errors;

	public AbstractException(HttpStatus status, String name, String message) {
		ObjectError error = new ObjectError(status, name, message);
		this.addError(error);
	}

	public Errors getErrors() {
		return errors;
	}

	public void addError(ObjectError error) {
		if (errors == null) {
			errors = new Errors();
		}
		this.errors.addError(error);
	}

}
