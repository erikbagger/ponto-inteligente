package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.impl;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Lancamento;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.mapper.LancamentoMapper;
import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.LancamentoController;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.LancamentoDto;
import br.com.udemy.erikbagger.pontointeligente.api.service.FuncionarioService;
import br.com.udemy.erikbagger.pontointeligente.api.service.LancamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Component
public class LancamentoControllerImpl implements LancamentoController {

	private static final Logger log = LoggerFactory.getLogger(LancamentoControllerImpl.class);

	@Value("${page.size}")
	private int pageSize;

	private final LancamentoService lancamentoService;

	private final FuncionarioService funcionarioService;

	public LancamentoControllerImpl(LancamentoService lancamentoService, FuncionarioService funcionarioService) {
		this.lancamentoService = lancamentoService;
		this.funcionarioService = funcionarioService;
	}

	@Override
	public ResponseEntity<Page<LancamentoDto>> buscarPorFuncionarioId(@PathVariable("id") Long id,
			@RequestParam(name = "index", required = false, defaultValue = "0") int index,
			@RequestParam(name = "dir", required = false, defaultValue = "ASC") String direction)
			throws NotFoundException {
		log.info("Recebendo um id para buscar Lancamentos por Funcionario: {}", id);

		PageRequest request = new PageRequest(index, pageSize, Direction.valueOf(direction), "id");

		Page<Lancamento> lancamentos = this.lancamentoService.findByFuncionarioId(id, request);
		Page<LancamentoDto> dtos = lancamentos.map(LancamentoMapper::convertToDto);

		log.info("Retornando pagina de Lancamentos: {}", dtos);
		return ResponseEntity.ok().body(dtos);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Override
	public ResponseEntity<String> excluir(@PathVariable("id") Long id) throws NotFoundException{
		log.info("Recebendo um id para excluir um Lancamento: {}", id);
		
		this.lancamentoService.excluir(id);
		
		log.info("Lancamento excluído com sucesso para o id: {}", id);
		return ResponseEntity.ok().body("Registro excluído com sucesso");
	}

	@Override
	public ResponseEntity<LancamentoDto> cadastrar(@Valid @RequestBody LancamentoDto lancamentoDto) throws NotFoundException, BadRequestException{
		log.info("Recebendo um LancamentoDto para cadastrar: {}", lancamentoDto);
		
		Funcionario funcionario = this.funcionarioService.buscarPorId(lancamentoDto.getFuncionarioId()).orElseThrow(() ->
				new NotFoundException("Funcionario não encontrado!" ));
		Lancamento lancamento = LancamentoMapper.convertToEntity(lancamentoDto);
		lancamento.setFuncionario(funcionario);
		
		lancamento = lancamentoService.cadastrar(lancamento);
		lancamentoDto = LancamentoMapper.convertToDto(lancamento);
			
		log.info("Lancamento cadastrado com sucesso: {}", lancamentoDto);
		return ResponseEntity.ok().body(lancamentoDto);
	}
	
	@Override
	public ResponseEntity<LancamentoDto> atualizar(@Valid @RequestBody LancamentoDto lancamentoDto)
			throws NotFoundException, BadRequestException {
		log.info("Recebendo um LancamentoDto para atualizar: {}", lancamentoDto);
		
		Funcionario funcionario = this.funcionarioService.buscarPorId(lancamentoDto.getFuncionarioId()).orElseThrow(() ->
				new NotFoundException("Funcionario não encontrado!" ));
		Lancamento lancamento = LancamentoMapper.convertToEntity(lancamentoDto);
		lancamento.setFuncionario(funcionario);
		
		lancamento = lancamentoService.atualizar(lancamento);
		lancamentoDto = LancamentoMapper.convertToDto(lancamento);
			
		log.info("Lancamento atualizado com sucesso: {}", lancamentoDto);
		return ResponseEntity.ok().body(lancamentoDto);
	}

}
