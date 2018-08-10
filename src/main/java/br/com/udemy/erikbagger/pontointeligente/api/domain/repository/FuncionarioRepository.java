package br.com.udemy.erikbagger.pontointeligente.api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;

@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Optional<Funcionario> findByCpf(String cpf);

	Optional<Funcionario> findByEmail(String email);

	Optional<Funcionario> findByCpfOrEmail(String cpf, String email);
}
