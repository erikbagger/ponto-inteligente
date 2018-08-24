package br.com.udemy.erikbagger.pontointeligente.api.domain.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.udemy.erikbagger.pontointeligente.api.domain.controller.CadastroPJController;
import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.CadastroPJDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.mapper.CadastroPJMapper;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.EmpresaService;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.FuncionarioService;

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
	public ResponseEntity<CadastroPJDto> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto,
			BindingResult result) throws BadRequestException, NotFoundException {
		log.info("Recebendo um objeto para efetuar o cadastro da Empresa: {}", cadastroPJDto);

		if (result.hasErrors()) {
			log.error("Erros de validação encontrados no CadastroPJDto: {}", cadastroPJDto);
			List<String> messages = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
			throw new BadRequestException("ERRO DE VALIDAÇÃO", messages);
		}

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
