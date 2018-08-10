package br.com.udemy.erikbagger.pontointeligente.api.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.repository.EmpresaRepository;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private EmpresaRepository repository;

	@Override
	public Empresa findByCnpj(String cnpj) {
		log.info("Recebendo um CNPJ para realizar a busca por Empresa: {}", cnpj);

		Optional<Empresa> empresa = this.repository.findByCnpj(cnpj);

		log.info("Retornando um registro de Empresa: {}", empresa.get().toString());
		return empresa.get();
	}

	@Override
	public Empresa persist(Empresa empresa) {
		log.info("Recebendo uma Empresa para persistir: {}", empresa.toString());

		empresa = this.repository.save(empresa);

		log.info("Retornando objeto persistindo: {}", empresa);
		return empresa;
	}

	@Override
	public Empresa update(Empresa empresa) {
		log.info("Recebendo uma Empresa para atualizar: {}", empresa.toString());

		empresa = this.repository.save(empresa);

		log.info("Retornando objeto atualizado: {}", empresa);
		return empresa;
	}

	@Override
	public Long deleteByCnpj(String cnpj) {
		log.info("Recebendo um CNPJ para exclusão de uma Empresa: {}");

		Optional<Empresa> empresa = Optional.ofNullable(this.findByCnpj(cnpj));

		Long id = empresa.get().getId();

		this.repository.delete(empresa.get());

		log.info("Registro de Empresa excluido com o id: {} e CNPJ: {}", id, cnpj);
		return id;
	}

	@Override
	public List<Empresa> findAll() {
		log.info("Efetuando a busca por uma lista de Empresa");

		Optional<List<Empresa>> empresas = Optional.ofNullable(this.repository.findAll());

		log.info("Retornando uma lista de Empresas");
		return empresas.get();
	}

}
