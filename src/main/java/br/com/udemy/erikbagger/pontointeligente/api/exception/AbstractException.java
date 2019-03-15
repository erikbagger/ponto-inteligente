package br.com.udemy.erikbagger.pontointeligente.api.exception;

import java.util.List;

public abstract class AbstractException extends Exception {

	private static final long serialVersionUID = -2939655698084299553L;

	private Errors errors;

	public AbstractException(String name, String... messages) {
		for (String message : messages) {
			ObjectError error = new ObjectError(name, message);
			this.addError(error);
		}
	}

	public AbstractException(String name, List<String> messages) {
		messages.forEach(m -> this.addError(new ObjectError(name, m)));
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
