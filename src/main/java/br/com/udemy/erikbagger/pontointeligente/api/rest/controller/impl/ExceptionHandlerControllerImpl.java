package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.impl;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.ExceptionHandlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.Errors;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;

@Component
public class ExceptionHandlerControllerImpl implements ExceptionHandlerController {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@Override
	public ResponseEntity<Errors> businessExceptionHandler(BadRequestException e) {
		log.error("Erros relacionados a regra de negócio encontrados: {}", e.getErrors().getMessages());
		Errors errors = e.getErrors();

		return ResponseEntity.badRequest().body(errors);
	}

	@Override
	public ResponseEntity<Errors> notFoundExceptionHandler(NotFoundException e) {
		log.error("Recurso não encontrado: {}", e.getErrors().getErrorList());
		Errors errors = e.getErrors();

		return ResponseEntity.status(404).body(errors);
	}

	@Override
	public ResponseEntity<String> internalErrorExceptionHandler(RuntimeException e) {
		log.error("Uma exceção não esperada aconteceu: {}", e.getMessage());
		String message = "Internal Server Error - Please contact the system administrator";

		return ResponseEntity.status(500).body(message);
	}

}
