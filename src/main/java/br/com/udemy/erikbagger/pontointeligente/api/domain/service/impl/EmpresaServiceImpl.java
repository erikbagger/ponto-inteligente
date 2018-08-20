package br.com.udemy.erikbagger.pontointeligente.api.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.repository.EmpresaRepository;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	private final EmpresaRepository repository;

	public EmpresaServiceImpl(EmpresaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Empresa> findByCnpj(String cnpj) throws NotFoundException {
		log.info("Recebendo um CNPJ para realizar a busca por Empresa: {}", cnpj);

		Optional<Empresa> empresa = this.repository.findByCnpj(cnpj);

		log.info("Retornando um registro de Empresa: {}", empresa);
		return empresa;
	}

	@Override
	public Empresa persist(Empresa entity) throws NotFoundException, BusinessException {
		log.info("Recebendo uma Empresa para persistir: {}", entity.toString());

		Optional<Empresa> empresa = this.findByCnpj(entity.getCnpj());

		if (empresa.isPresent()) {
			log.error("Empresa já existente: {}", empresa);
			throw new BusinessException("Erro ao salvar", "Empresa já cadastrada");
		}

		entity = this.repository.save(entity);

		log.info("Retornando objeto persistindo: {}", entity);
		return entity;
	}

	@Override
	public Empresa update(Empresa entity) throws NotFoundException {
		log.info("Recebendo uma Empresa para atualizar: {}", entity.toString());

		Optional.ofNullable(this.repository.findOne(entity.getId()))
				.orElseThrow(() -> new NotFoundException("Empresa não encontrada"));

		entity = this.repository.save(entity);

		log.info("Retornando objeto atualizado: {}", entity);
		return entity;
	}

	@Override
	public void deleteByCnpj(String cnpj) throws NotFoundException {
		log.info("Recebendo um CNPJ para exclusão de uma Empresa: {}");

		Empresa empresa = this.findByCnpj(cnpj).orElseThrow(() -> new NotFoundException("Empresa não encontrada"));

		Long id = empresa.getId();

		this.repository.delete(empresa);

		log.info("Registro de Empresa excluido com o id: {} e CNPJ: {}", id, cnpj);
	}

	@Override
	public List<Empresa> findAll() throws NotFoundException {
		log.info("Efetuando a busca por uma lista de Empresa");

		List<Empresa> entities = Optional.ofNullable(this.repository.findAll()).orElseThrow(() -> new NotFoundException("NAO ENCONTRADO", "Nenhum registro encontrado"));

		log.info("Retornando uma lista de Empresas: {}", entities);
		return entities;
	}

}
