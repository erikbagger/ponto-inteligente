package br.com.udemy.erikbagger.pontointeligente.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto.CadastroPFDto;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;

@RestController
@RequestMapping("/cadastro-pf")
@CrossOrigin("*")
public interface CadastroPFController {

	@PostMapping
	public ResponseEntity<CadastroPFDto> cadastrar(CadastroPFDto cadastroPFDto, BindingResult result) throws BadRequestException, NotFoundException;
}
