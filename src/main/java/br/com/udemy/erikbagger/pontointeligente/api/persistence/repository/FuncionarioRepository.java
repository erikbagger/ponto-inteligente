package br.com.udemy.erikbagger.pontointeligente.api.persistence.repository;

import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Optional<Funcionario> findByCpf(String cpf);

	Optional<Funcionario> findByEmail(String email);

	Optional<Funcionario> findByCpfOrEmail(String cpf, String email);
}
