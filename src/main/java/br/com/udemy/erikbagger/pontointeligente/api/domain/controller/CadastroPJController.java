package br.com.udemy.erikbagger.pontointeligente.api.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.CadastroPJDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;

//Cadastra uma empresa e o seu administrador
@RestController
@RequestMapping("/cadastro-pj")
@CrossOrigin(origins = "*")
public interface CadastroPJController {

	@PostMapping
	ResponseEntity<CadastroPJDto> cadastrar(CadastroPJDto cadastroPJDto, BindingResult result) throws BadRequestException, NotFoundException;

}
