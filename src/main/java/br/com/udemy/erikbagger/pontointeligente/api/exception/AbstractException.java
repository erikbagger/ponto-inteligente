package br.com.udemy.erikbagger.pontointeligente.api.exception;

import org.springframework.validation.FieldError;

import java.util.List;

public abstract class AbstractException extends Exception {

	private static final long serialVersionUID = -2939655698084299553L;

	private String code;
	private String message;
	private List<FieldError> errors;

	public AbstractException(String code, String message){
		this.code = code;
		this.message = message;
	}

	public AbstractException(String code, List<FieldError> fieldErrors){
		this.code = code;
		this.errors = fieldErrors;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AbstractException{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", errors=" + errors +
				'}';
	}
}
