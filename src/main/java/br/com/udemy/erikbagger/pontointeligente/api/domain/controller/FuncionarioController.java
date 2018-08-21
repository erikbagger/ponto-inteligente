package br.com.udemy.erikbagger.pontointeligente.api.domain.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.FuncionarioDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;

@RestController
@RequestMapping("/funcionario")
public interface FuncionarioController {


	@PutMapping
	public ResponseEntity<FuncionarioDto> atualizar(FuncionarioDto funcionarioDto, BindingResult result) throws BusinessException, NotFoundException;
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDto>> listar() throws NotFoundException;
	
	@DeleteMapping("{cpf}")
	public ResponseEntity<String> deleteByCpf(String cpf) throws NotFoundException;
}
