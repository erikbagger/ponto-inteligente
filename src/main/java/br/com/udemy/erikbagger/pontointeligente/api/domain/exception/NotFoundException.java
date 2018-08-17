package br.com.udemy.erikbagger.pontointeligente.api.domain.exception;

public class NotFoundException extends AbstractException {

	private static final long serialVersionUID = -4780529715403655848L;
	
	private static final String NAME = "NÃO ENCONTRADO";

	public NotFoundException(String... messages) {
		super(NAME, messages);
	}
}
