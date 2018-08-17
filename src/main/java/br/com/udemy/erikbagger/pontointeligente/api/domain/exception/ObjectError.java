package br.com.udemy.erikbagger.pontointeligente.api.domain.exception;

public class ObjectError {

	private String name;
	private String message;

	public ObjectError(String name, String message) {
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
