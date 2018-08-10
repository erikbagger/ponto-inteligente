package br.com.udemy.erikbagger.pontointeligente.api.domain.exception;

import org.springframework.http.HttpStatus;

public class NegocioException extends AbstractException {

	private static final long serialVersionUID = -1185686385573245696L;
	
	private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
	
	public NegocioException(String name, String message) {
		super(STATUS, name, message);
	}

}
