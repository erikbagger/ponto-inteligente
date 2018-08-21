package br.com.udemy.erikbagger.pontointeligente.api.domain.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.udemy.erikbagger.pontointeligente.api.domain.controller.FuncionarioController;
import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.FuncionarioDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.mapper.FuncionarioMapper;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.FuncionarioService;

@Component
public class FuncionarioControllerImpl implements FuncionarioController{

	private static final Logger log = LoggerFactory.getLogger(FuncionarioControllerImpl.class);

	private final FuncionarioService service;

	public FuncionarioControllerImpl(FuncionarioService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<FuncionarioDto> atualizar(@Valid @RequestBody FuncionarioDto funcionarioDto, BindingResult result) throws BusinessException, NotFoundException {
		log.info("Recebendo um FuncionarioDto para atualizar: {}", funcionarioDto);
		
		if(result.hasErrors() ) {
			log.error("Erros de validação encontrados em FuncionarioDto: {}", funcionarioDto);
			List<String> erros = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
			throw new BusinessException("ERROS DE VALIDAÇÃO", erros);
		}
		
		Funcionario funcionario = FuncionarioMapper.convertToEntity(funcionarioDto);
		funcionario = this.service.update(funcionario);
		
		funcionarioDto = FuncionarioMapper.convertToDto(funcionario);
		
		log.info("Retornando FuncionarioDto atualizado: {}", funcionarioDto);
		return ResponseEntity.ok().body(funcionarioDto);
	}

	@Override
	public ResponseEntity<List<FuncionarioDto>> listar() throws NotFoundException {
		log.info("Recebendo uma requisição para listar os Funcionarios");
		
		List<Funcionario> funcionarios = this.service.listar();
		List<FuncionarioDto> dtos = funcionarios.stream().map(FuncionarioMapper::convertToDto).collect(Collectors.toList());
		
		log.info("Retornando uma lista de FuncionarioDto: {}", dtos);
		return ResponseEntity.ok().body(dtos);
	}

	@Override
	public ResponseEntity<String> deleteByCpf(@PathVariable ("cpf") String cpf) throws NotFoundException {
		log.info("Recebendo um CPF para efetuar a exclusão: {}", cpf);
		
		this.service.deleteByCpf(cpf);
		
		log.info("Registro excluído com sucesso para o CPF: {}", cpf);
		return ResponseEntity.ok().body("Registro excluído com sucesso");
	}
	
}
