package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import java.util.Optional;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;

public interface FuncionarioService {

	Optional<Funcionario> findByCpf(String cpf);

	Optional<Funcionario> findByEmail(String email);

	Optional<Funcionario> findByCpfOrEmail(String cpf, String email);

	Funcionario persist(Funcionario funcionario) throws BusinessException;

	Funcionario update(Funcionario funcionario) throws NotFoundException, BusinessException;

	void deleteByCpf(String cpf) throws NotFoundException;
}
