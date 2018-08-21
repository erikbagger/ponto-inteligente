package br.com.udemy.erikbagger.pontointeligente.api.domain.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.FuncionarioDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;

@RestController
@RequestMapping("/funcionario")
public interface FuncionarioController {


	@PutMapping
	public ResponseEntity<FuncionarioDto> atualizar(@Valid @RequestBody FuncionarioDto funcionarioDto, BindingResult result) throws BusinessException, NotFoundException;
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDto>> listar();
}
