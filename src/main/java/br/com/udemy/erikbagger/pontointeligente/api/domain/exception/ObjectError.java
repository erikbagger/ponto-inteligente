package br.com.udemy.erikbagger.pontointeligente.api.domain.exception;

import org.springframework.http.HttpStatus;

public class ObjectError {

	private HttpStatus status;
	private String name;
	private String message;

	public ObjectError(HttpStatus status, String name, String message) {
		this.status = status;
		this.name = name;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
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
