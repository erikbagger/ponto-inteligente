package br.com.udemy.erikbagger.pontointeligente.api.domain.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.CadastroPJDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.AbstractException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BusinessException;

//Cadastra uma empresa e o seu administrador
@RestController
@RequestMapping("/cadastro-pj")
@CrossOrigin(origins = "*")
public interface CadastroPJController {

	@PostMapping
	ResponseEntity<CadastroPJDto> cadastrar(CadastroPJDto cadastroPJDto, BindingResult result) throws BusinessException, NotFoundException;

}
