package br.com.udemy.erikbagger.pontointeligente.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto.CadastroPJDto;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;

//Cadastra uma empresa e o seu administrador
@RestController
@RequestMapping("/cadastro-pj")
@CrossOrigin(origins = "*")
public interface CadastroPJController {

	@PostMapping
	ResponseEntity<CadastroPJDto> cadastrar(CadastroPJDto cadastroPJDto) throws BadRequestException, NotFoundException;

}
