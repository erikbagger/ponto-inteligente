package br.com.udemy.erikbagger.pontointeligente.api.domain.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.repository.FuncionarioRepository;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

	private final FuncionarioRepository repository;
	
	@Autowired
	public FuncionarioServiceImpl(FuncionarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Funcionario> findByCpf(String cpf) {
		log.info("Recebendo um CPF para efetuar a busca por um Funcionario: {}", cpf);

		Optional<Funcionario> funcionario = this.repository.findByCpf(cpf);

		log.info("Retornando um objeto Funcionario: {}", funcionario.get().toString());
		return funcionario;
	}

	@Override
	public Optional<Funcionario> findByEmail(String email) {
		log.info("Recebendo um email para efetuar a busca por um Funcionario: {}", email);

		Optional<Funcionario> funcionario = this.repository.findByEmail(email);

		log.info("Retornando um objeto Funcionario: {}", funcionario.get().toString());
		return funcionario;
	}

	@Override
	public Optional<Funcionario> findByCpfOrEmail(String cpf, String email) {
		log.info("Recebendo um CPF ou um email para efetuar a busca por um Funcionario: [cpf = {}, email = {}]", cpf,
				email);

		Optional<Funcionario> funcionario = this.repository.findByCpfOrEmail(cpf, email);

		log.info("Retornando um objeto Funcionario: {}", funcionario);
		return funcionario;
	}

	@Override
	public Funcionario persist(Funcionario entity) throws BusinessException {
		log.info("Recebendo um Funcionario para persistir: {}", entity.toString());

		Optional<Funcionario> funcionario = this.findByCpfOrEmail(entity.getCpf(), entity.getEmail());
		
		if(funcionario.isPresent()) {
			log.error("Encontrado um registro com o CPF ou email: {}", funcionario);
			throw new BusinessException("Funcionario já cadastrado. Verifique o CPF e o email");
		}
		
		entity = this.repository.save(entity);

		log.info("Retornando um objeto Funcionario: {}", entity);
		return entity;
	}

	@Override
	public Funcionario update(Funcionario entity) throws NotFoundException {
		log.info("Recebendo objeto Funcionario para atualizar: {}", entity);
		
		this.findByCpfOrEmail(entity.getCpf(), entity.getEmail()).orElseThrow(() -> new NotFoundException("Nenhum registro encontrado"));
		
		entity = this.repository.save(entity);

		log.info("Retornando um objeto Funcionario: {}", entity);
		return entity;
	}

	@Override
	public void deleteByCpf(String cpf) throws NotFoundException {
		log.info("Recebendo um CPF para remover um Funcionario: {}", cpf);

		Funcionario funcionario = this.repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException(""));

		Long id = funcionario.getId();

		this.repository.delete(funcionario);
		log.info("Objeto Funcionario removido com sucesso com o id: {}, e CPF: {}", id, cpf);
	}

}
