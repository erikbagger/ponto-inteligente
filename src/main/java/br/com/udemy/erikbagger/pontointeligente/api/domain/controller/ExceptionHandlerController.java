package br.com.udemy.erikbagger.pontointeligente.api.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.Errors;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;

@ControllerAdvice
public interface ExceptionHandlerController {

	@ExceptionHandler(value = BadRequestException.class)
	ResponseEntity<Errors> businessExceptionHandler(BadRequestException e);
	
	@ExceptionHandler(value = NotFoundException.class)
	ResponseEntity<Errors> notFoundExceptionHandler(NotFoundException e);
	
	@ExceptionHandler(value = RuntimeException.class)
	ResponseEntity<String> internalErrorExceptionHandler(RuntimeException e);
	
}
