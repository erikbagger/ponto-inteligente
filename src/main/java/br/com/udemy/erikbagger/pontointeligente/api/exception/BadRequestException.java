package br.com.udemy.erikbagger.pontointeligente.api.exception;

import java.util.List;

public class BadRequestException extends AbstractException {

	private static final long serialVersionUID = -1185686385573245696L;

	public BadRequestException(String name, String... messages) {
		super(name, messages);
	}
	
	public BadRequestException(String name, List<String> messages) {
		super(name, messages);
	}
}
