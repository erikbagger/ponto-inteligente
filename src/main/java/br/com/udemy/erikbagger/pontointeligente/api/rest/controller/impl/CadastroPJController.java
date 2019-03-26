package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.impl;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.CadastroPJDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Cadastra uma empresa e o seu administrador
@RestController
@RequestMapping("/cadastro-pj")
@CrossOrigin(origins = "*")
public interface CadastroPJController {

	@PostMapping
	ResponseEntity<CadastroPJDto> cadastrar(CadastroPJDto cadastroPJDto) throws BadRequestException, NotFoundException;

}
