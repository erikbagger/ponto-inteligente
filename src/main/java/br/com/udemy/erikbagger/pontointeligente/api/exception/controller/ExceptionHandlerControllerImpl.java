package br.com.udemy.erikbagger.pontointeligente.api.exception.controller;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.InternalServerError;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.wrapper.ExceptionWrapper;
import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.ExceptionHandlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Component
public class ExceptionHandlerControllerImpl extends ResponseEntityExceptionHandler implements ExceptionHandlerController {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@Override
	public ResponseEntity<ExceptionWrapper> businessExceptionHandler(BusinessException e) {
		log.error("Erros relacionados a regra de negócio encontrados: {}", e.getMessage());

		return new ResponseEntity<>(new ExceptionWrapper(e), UNPROCESSABLE_ENTITY);
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

	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Erros relacionados a requisicao foram encontrados: {}", ex.getBindingResult());

		return new ResponseEntity<>(new ExceptionWrapper(new BadRequestException("", ex.getBindingResult().getFieldErrors())), BAD_REQUEST);
	}

}
