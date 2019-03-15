package br.com.udemy.erikbagger.pontointeligente.api.rest.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto.LancamentoDto;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;

@RestController
@RequestMapping("/lancamento")
public interface LancamentoController {

	@GetMapping("{id}")
	ResponseEntity<Page<LancamentoDto>> buscarPorFuncionarioId(Long id, int index, String direction) throws NotFoundException;
	
	@DeleteMapping("{id}")
	ResponseEntity<String> excluir(Long id) throws NotFoundException;
	
	@PostMapping
	ResponseEntity<LancamentoDto> cadastrar(LancamentoDto lancamentoDto, BindingResult result) throws BadRequestException;

	@PutMapping
	ResponseEntity<LancamentoDto> atualizar(LancamentoDto lancamentoDto, BindingResult result) throws BadRequestException;
}
