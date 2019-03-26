package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.impl;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.LancamentoDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lancamento")
public interface LancamentoController {

	@GetMapping("{id}")
	ResponseEntity<Page<LancamentoDto>> buscarPorFuncionarioId(Long id, int index, String direction) throws NotFoundException;
	
	@DeleteMapping("{id}")
	ResponseEntity<String> excluir(Long id) throws NotFoundException;
	
	@PostMapping
	ResponseEntity<LancamentoDto> cadastrar(LancamentoDto lancamentoDto) throws NotFoundException, BadRequestException;

	@PutMapping
	ResponseEntity<LancamentoDto> atualizar(LancamentoDto lancamentoDto) throws NotFoundException, BadRequestException;
}
