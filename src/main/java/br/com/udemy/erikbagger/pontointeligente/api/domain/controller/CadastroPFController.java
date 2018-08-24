package br.com.udemy.erikbagger.pontointeligente.api.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.CadastroPFDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;

@RestController
@RequestMapping("/cadastro-pf")
@CrossOrigin("*")
public interface CadastroPFController {

	@PostMapping
	public ResponseEntity<CadastroPFDto> cadastrar(CadastroPFDto cadastroPFDto, BindingResult result) throws BadRequestException, NotFoundException;
}
