package br.com.udemy.erikbagger.pontointeligente.api.exception;

import org.springframework.validation.FieldError;

import java.util.List;

public class BadRequestException extends AbstractException {

	private static final long serialVersionUID = -1185686385573245696L;

	public BadRequestException(String code, List<FieldError> errors) {
		super(code, errors);
	}
}
