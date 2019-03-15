package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.impl;

import br.com.udemy.erikbagger.pontointeligente.api.exception.ExceptionWrapper;
import br.com.udemy.erikbagger.pontointeligente.api.exception.InternalServerError;
import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.ExceptionHandlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class ExceptionHandlerControllerImpl implements ExceptionHandlerController {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@Override
	public ResponseEntity<ExceptionWrapper> businessExceptionHandler(BadRequestException e) {
		log.error("Erros relacionados a regra de negócio encontrados: {}", e.getMessage());

		return new ResponseEntity<>(new ExceptionWrapper(e), BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ExceptionWrapper> notFoundExceptionHandler(NotFoundException e) {
		log.error("Recurso não encontrado: {}", e.getMessage());

		return new ResponseEntity<>(new ExceptionWrapper(e), NOT_FOUND);
	}

	@Override
	public ResponseEntity<ExceptionWrapper> internalServerErrorHandler(Exception e) {
		log.error("Uma exception ocorreu: {}", e.getMessage());

		return new ResponseEntity<>(new ExceptionWrapper(new InternalServerError()), INTERNAL_SERVER_ERROR);
	}

}
