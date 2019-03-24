package br.com.udemy.erikbagger.pontointeligente.api.rest.controller;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.wrapper.ExceptionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;

@ControllerAdvice
public interface ExceptionHandlerController {

	@ExceptionHandler(value = BadRequestException.class)
	ResponseEntity<ExceptionWrapper> businessExceptionHandler(BusinessException e);
	
	@ExceptionHandler(value = NotFoundException.class)
	ResponseEntity<ExceptionWrapper> notFoundExceptionHandler(NotFoundException e);

	@ExceptionHandler(value = Exception.class)
	ResponseEntity<ExceptionWrapper> internalServerErrorHandler(Exception e);
	
}
