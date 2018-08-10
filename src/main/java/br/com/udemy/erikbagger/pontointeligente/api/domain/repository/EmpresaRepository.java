package br.com.udemy.erikbagger.pontointeligente.api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	@Transactional(readOnly = true)
	Optional<Empresa> findByCnpj(String cnpj);
}
