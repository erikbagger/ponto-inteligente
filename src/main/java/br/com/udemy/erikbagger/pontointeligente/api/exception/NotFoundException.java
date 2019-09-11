package br.com.udemy.erikbagger.pontointeligente.api.exception;

public class NotFoundException extends AbstractException {

	private static final long serialVersionUID = -4780529715403655848L;
	
	private static final String CODE = "NAO ENCONTRADO";

	public NotFoundException(String message) {
		super(CODE, message);
	}
}
