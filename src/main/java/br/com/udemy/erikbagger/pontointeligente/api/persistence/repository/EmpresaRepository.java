package br.com.udemy.erikbagger.pontointeligente.api.persistence.repository;

import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	@Transactional(readOnly = true)
	Optional<Empresa> findByCnpj(String cnpj);
}
