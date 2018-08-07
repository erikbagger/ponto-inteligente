package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import java.util.Optional;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;

public interface FuncionarioService {

	Optional<Funcionario> findByCpf(String cpf);

	Optional<Funcionario> findByEmail(String email);

	Optional<Funcionario> findByCpfOrEmail(String cpf, String email);
	
	Funcionario persist(Funcionario funcionario);
	
	Funcionario update(Funcionario funcionario);
	
	void deleteByCpf(String cpf);
}
