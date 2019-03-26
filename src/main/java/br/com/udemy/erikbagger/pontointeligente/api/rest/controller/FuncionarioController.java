package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.impl;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.FuncionarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public interface FuncionarioController {


	@PutMapping
	public ResponseEntity<FuncionarioDto> atualizar(FuncionarioDto funcionarioDto) throws BadRequestException, NotFoundException;
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDto>> listar() throws NotFoundException;
	
	@DeleteMapping("{cpf}")
	public ResponseEntity<String> deleteByCpf(String cpf) throws NotFoundException;
}
