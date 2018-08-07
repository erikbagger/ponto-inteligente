package br.com.udemy.erikbagger.pontointeligente.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;

@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Funcionario findByCpf(String cpf);

	Funcionario findByEmail(String email);

	Funcionario findByCpfOrEmail(String cpf, String email);
}
