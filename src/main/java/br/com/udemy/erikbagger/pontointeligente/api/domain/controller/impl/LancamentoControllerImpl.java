package br.com.udemy.erikbagger.pontointeligente.api.domain.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.udemy.erikbagger.pontointeligente.api.domain.controller.LancamentoController;
import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.LancamentoDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Lancamento;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.mapper.LancamentoMapper;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.FuncionarioService;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.LancamentoService;

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

	@Override
	public ResponseEntity<String> excluir(@PathVariable("id") Long id) throws NotFoundException{
		log.info("Recebendo um id para excluir um Lancamento: {}", id);
		
		this.lancamentoService.excluir(id);
		
		log.info("Lancamento excluído com sucesso para o id: {}", id);
		return ResponseEntity.ok().body("Registro excluído com sucesso");
	}

	@Override
	public ResponseEntity<LancamentoDto> cadastrar(@Valid @RequestBody LancamentoDto lancamentoDto, BindingResult result)
			throws BadRequestException {
		log.info("Recebendo um LancamentoDto para cadastrar: {}", lancamentoDto);

		if(result.hasErrors()) {
			log.error("Erros de validação encontrados no dto: {}", lancamentoDto);
			List<String> erros = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
			throw new BadRequestException("Erro de validação", erros);
		}
		
		Funcionario funcionario = this.funcionarioService.buscarPorId(lancamentoDto.getFuncionarioId()).orElseThrow(() -> new BadRequestException("Funcionario não encontrado!", ""));
		Lancamento lancamento = LancamentoMapper.convertToEntity(lancamentoDto);
		lancamento.setFuncionario(funcionario);
		
		lancamento = lancamentoService.cadastrar(lancamento);
		lancamentoDto = LancamentoMapper.convertToDto(lancamento);
			
		log.info("Lancamento cadastrado com sucesso: {}", lancamentoDto);
		return ResponseEntity.ok().body(lancamentoDto);
	}
	
	@Override
	public ResponseEntity<LancamentoDto> atualizar(@Valid @RequestBody LancamentoDto lancamentoDto, BindingResult result)
			throws BadRequestException {
		log.info("Recebendo um LancamentoDto para atualizar: {}", lancamentoDto);

		if(result.hasErrors()) {
			log.error("Erros de validação encontrados no dto: {}", lancamentoDto);
			List<String> erros = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
			throw new BadRequestException("Erro de validação", erros);
		}
		
		Funcionario funcionario = this.funcionarioService.buscarPorId(lancamentoDto.getFuncionarioId()).orElseThrow(() -> new BadRequestException("Funcionario não encontrado!" ));
		Lancamento lancamento = LancamentoMapper.convertToEntity(lancamentoDto);
		lancamento.setFuncionario(funcionario);
		
		lancamento = lancamentoService.atualizar(lancamento);
		lancamentoDto = LancamentoMapper.convertToDto(lancamento);
			
		log.info("Lancamento atualizado com sucesso: {}", lancamentoDto);
		return ResponseEntity.ok().body(lancamentoDto);
	}

}
