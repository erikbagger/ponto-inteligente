package br.com.udemy.erikbagger.pontointeligente.api.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto.FuncionarioDto;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;

@RestController
@RequestMapping("/funcionario")
public interface FuncionarioController {


	@PutMapping
	public ResponseEntity<FuncionarioDto> atualizar(FuncionarioDto funcionarioDto, BindingResult result) throws BadRequestException, NotFoundException;
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDto>> listar() throws NotFoundException;
	
	@DeleteMapping("{cpf}")
	public ResponseEntity<String> deleteByCpf(String cpf) throws NotFoundException;
}
