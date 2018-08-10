package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;

public interface FuncionarioService {

	Funcionario findByCpf(String cpf);

	Funcionario findByEmail(String email);

	Funcionario findByCpfOrEmail(String cpf, String email);

	Funcionario persist(Funcionario funcionario);

	Funcionario update(Funcionario funcionario);

	void deleteByCpf(String cpf);
}
