package br.com.udemy.erikbagger.pontointeligente.api.domain.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.udemy.erikbagger.pontointeligente.api.domain.controller.EmpresaController;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.EmpresaService;

@Component
public class EmpresaControllerImpl implements EmpresaController {

	private static final Logger log = LoggerFactory.getLogger(EmpresaControllerImpl.class);

	@Autowired
	private EmpresaService service;

	@Override
	public ResponseEntity<Empresa> cadastrar(@RequestBody Empresa empresa, BindingResult result) {
		log.info("Recebendo um objeto para efetuar o cadastro da Empresa: {}", empresa);

		empresa = this.service.persist(empresa);

		log.info("Empresa cadastrada: {}", empresa);
		return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
	}

	@Override
	public ResponseEntity<Empresa> buscarPorCnpj(@PathVariable String cnpj) {
		log.info("Recebendo um CNPJ para efetuar a busca: {}", cnpj);

		Empresa empresa = this.service.findByCnpj(cnpj);

		log.info("Retornando objeto Empresa: {}", empresa);
		return ResponseEntity.ok(empresa);
	}

	@Override
	public ResponseEntity<List<Empresa>> listar() {
		log.info("Recebendo uma requisição para listar todas as empresas");

		List<Empresa> empresas = this.service.findAll();

		log.info("Retornando lista de Empresa: {}", empresas);
		return ResponseEntity.ok(empresas);
	}

	@Override
	public ResponseEntity<Empresa> atualizar(@RequestBody Empresa empresa) {
		log.info("Recebendo um objeto Empresa para atualizar: {}", empresa.toString());

		empresa = this.service.update(empresa);

		log.info("Retornando objeto Empresa: {}", empresa.toString());
		return ResponseEntity.ok(empresa);
	}

	@Override
	public ResponseEntity<String> excluir(@PathVariable String cnpj) {
		log.info("Recebendo um CNPJ exclusão: {}", cnpj);

		Long id = this.service.deleteByCnpj(cnpj);

		log.info("Recurso excluído com o id: {}", id);
		return ResponseEntity.ok().body("Empresa excluída com sucesso");
	}

}
