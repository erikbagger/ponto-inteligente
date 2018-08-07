package br.com.udemy.erikbagger.pontointeligente.api.domain.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.repository.FuncionarioRepository;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

	@Autowired
	private FuncionarioRepository repository;

	@Override
	public Optional<Funcionario> findByCpf(String cpf) {
		log.info("Recebendo um CPF para efetuar a busca por um Funcionario: {}", cpf);

		Optional<Funcionario> funcionario = Optional.ofNullable(this.repository.findByCpf(cpf));

		log.info("Retornando um objeto Funcionario: {}", funcionario.get().toString());
		return funcionario;
	}

	@Override
	public Optional<Funcionario> findByEmail(String email) {
		log.info("Recebendo um email para efetuar a busca por um Funcionario: {}", email);

		Optional<Funcionario> funcionario = Optional.ofNullable(this.repository.findByEmail(email));

		log.info("Retornando um objeto Funcionario: {}", funcionario.get().toString());
		return funcionario;
	}

	@Override
	public Optional<Funcionario> findByCpfOrEmail(String cpf, String email) {
		log.info("Recebendo um CPF ou um email para efetuar a busca por um Funcionario: [cpf = {}, email = {}]", cpf,
				email);

		Optional<Funcionario> funcionario = Optional.ofNullable(this.repository.findByCpfOrEmail(cpf, email));

		log.info("Retornando um objeto Funcionario: {}", funcionario.get().toString());
		return funcionario;
	}

	@Override
	public Funcionario persist(Funcionario funcionario) {
		log.info("Recebendo um Funcionario para persistir: {}", funcionario.toString());

		funcionario = this.repository.save(funcionario);

		log.info("Retornando um objeto Funcionario: {}", funcionario.toString());
		return funcionario;
	}

	@Override
	public Funcionario update(Funcionario funcionario) {
		log.info("Recebendo objeto Funcionario para atualizar: {}", funcionario.toString());

		funcionario = this.repository.save(funcionario);

		log.info("Retornando um objeto Funcionario: {}", funcionario.toString());
		return funcionario;
	}

	@Override
	public void deleteByCpf(String cpf) {
		log.info("Recebendo um CPF para remover um Funcionario: {}", cpf);

		Optional<Funcionario> funcionario = Optional.ofNullable(this.repository.findByCpf(cpf));

		Long id = funcionario.get().getId();

		this.repository.delete(funcionario.get());
		log.info("Objeto Funcionario removido com sucesso com o id: {}, e CPF: {}", id, cpf);
	}

}
