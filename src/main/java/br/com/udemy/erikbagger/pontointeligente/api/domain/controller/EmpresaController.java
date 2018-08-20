package br.com.udemy.erikbagger.pontointeligente.api.domain.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.EmpresaDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;

@RestController
@RequestMapping("/empresa")
public interface EmpresaController {

	@GetMapping
	ResponseEntity<List<EmpresaDto>> listar() throws NotFoundException;

	@GetMapping("{cnpj}")
	ResponseEntity<EmpresaDto> buscarPorCnpj(String cnpj) throws NotFoundException;

	@DeleteMapping("{cnpj}")
	ResponseEntity<String> excluir(String cnpj) throws NotFoundException;
}
