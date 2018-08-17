package br.com.udemy.erikbagger.pontointeligente.api.domain.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.udemy.erikbagger.pontointeligente.api.domain.controller.EmpresaController;
import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.EmpresaDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.mapper.EmpresaMapper;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.EmpresaService;

@Component
public class EmpresaControllerImpl implements EmpresaController{
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaControllerImpl.class);
	
	private final EmpresaService service;
	
	@Autowired
	public EmpresaControllerImpl(EmpresaService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<List<EmpresaDto>> listar() throws NotFoundException {
		log.info("Recebendo uma requisição para listar todas as empresas");

		List<Empresa> empresas = this.service.findAll();
		List<EmpresaDto> dtos = empresas.stream().map(EmpresaMapper::convertToDto).collect(Collectors.toList());

		log.info("Retornando lista de Empresas: {}", dtos);
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<EmpresaDto> buscarPorCnpj(@PathVariable String cnpj) throws NotFoundException {
		log.info("Recebendo um CNPJ para efetuar a busca: {}", cnpj);

		Empresa empresa = this.service.findByCnpj(cnpj).orElseThrow(() -> new NotFoundException("Empresa não encontrada para o CNPJ: " + cnpj));
		EmpresaDto dto = EmpresaMapper.convertToDto(empresa);

		log.info("Registro de Empresa encontrado: {}", dto);
		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<String> excluir(String cnpj) throws NotFoundException {
		log.info("Recebendo um CNPJ para efetuar a exclusão: {}", cnpj);

		this.service.deleteByCnpj(cnpj);

		log.info("Registro de Empresa excluído com sucesso para o CNPJ: {}", cnpj);
		return ResponseEntity.ok("Registro excluído com o CNPJ: " +cnpj);
	}

}
