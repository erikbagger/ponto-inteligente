package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;

public interface FuncionarioService {
	
	Optional<Funcionario> buscarPorId(Long id);

	List<Funcionario> listar() throws NotFoundException;
	
	Optional<Funcionario> findByCpf(String cpf);

	Optional<Funcionario> findByEmail(String email);

	Optional<Funcionario> findByCpfOrEmail(String cpf, String email);

	Funcionario persist(Funcionario funcionario) throws BadRequestException;

	Funcionario update(Funcionario funcionario) throws NotFoundException, BadRequestException;

	void deleteByCpf(String cpf) throws NotFoundException;
}
