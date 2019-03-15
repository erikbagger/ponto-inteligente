package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.CadastroPJController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto.CadastroPJDto;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.mapper.CadastroPJMapper;
import br.com.udemy.erikbagger.pontointeligente.api.service.EmpresaService;
import br.com.udemy.erikbagger.pontointeligente.api.service.FuncionarioService;

@Component
public class CadastroPJControllerImpl implements CadastroPJController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPJControllerImpl.class);

	private final EmpresaService empresaService;

	private final FuncionarioService funcionarioService;
	
	public CadastroPJControllerImpl(EmpresaService empresaService, FuncionarioService funcionarioService) {
		this.empresaService = empresaService;
		this.funcionarioService = funcionarioService;
	}

	@Override
	public ResponseEntity<CadastroPJDto> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto) throws BadRequestException, NotFoundException {
		log.info("Recebendo um objeto para efetuar o cadastro da Empresa: {}", cadastroPJDto);

		Empresa empresa = (Empresa) CadastroPJMapper.convertToEntity(cadastroPJDto).get("empresa");
		empresa = this.empresaService.persist(empresa);

		Funcionario funcionario = (Funcionario) CadastroPJMapper.convertToEntity(cadastroPJDto).get("funcionario");
		funcionario.setEmpresa(empresa);
		funcionario = this.funcionarioService.persist(funcionario);

		cadastroPJDto = CadastroPJMapper.convertToDto(funcionario);

		log.info("Empresa cadastrada com sucesso: {}", cadastroPJDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroPJDto);
	}

}
