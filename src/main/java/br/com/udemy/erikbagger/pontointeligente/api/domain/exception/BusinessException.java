package br.com.udemy.erikbagger.pontointeligente.api.domain.exception;

import java.util.List;

public class BusinessException extends AbstractException {

	private static final long serialVersionUID = -1185686385573245696L;

	public BusinessException(String name, String... messages) {
		super(name, messages);
	}
	
	public BusinessException(String name, List<String> messages) {
		super(name, messages);
	}
}
