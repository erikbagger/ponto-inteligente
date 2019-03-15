package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.CadastroPFController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto.CadastroPFDto;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.mapper.CadastroPFMapper;
import br.com.udemy.erikbagger.pontointeligente.api.service.EmpresaService;
import br.com.udemy.erikbagger.pontointeligente.api.service.FuncionarioService;

@Component
public class CadastroPFControllerImpl implements CadastroPFController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPFControllerImpl.class);

	private final EmpresaService empresaService;

	private final FuncionarioService funcionarioService;

	public CadastroPFControllerImpl(EmpresaService empresaService, FuncionarioService funcionarioService) {
		this.empresaService = empresaService;
		this.funcionarioService = funcionarioService;
	}

	@Override
	public ResponseEntity<CadastroPFDto> cadastrar(@Valid @RequestBody CadastroPFDto cadastroPFDto) throws BadRequestException, NotFoundException {
		log.info("Recebendo um CadastroPFDto para persistir: {}", cadastroPFDto);
		
		String cnpj = cadastroPFDto.getCnpj();
		Empresa empresa = this.empresaService.findByCnpj(cnpj).orElseThrow(() -> new NotFoundException("Empresa não encontrada para o CNPJ: " +cnpj));
		
		Funcionario funcionario = CadastroPFMapper.convertToEntity(cadastroPFDto);
		funcionario.setEmpresa(empresa);
		funcionario = this.funcionarioService.persist(funcionario);
		
		cadastroPFDto = CadastroPFMapper.convertToDto(funcionario);
		
		log.info("Funcionario persistido com sucesso: {}", cadastroPFDto);
		return ResponseEntity.status(201).body(cadastroPFDto);
	}

}
