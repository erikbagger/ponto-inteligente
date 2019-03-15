package br.com.udemy.erikbagger.pontointeligente.api.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Lancamento;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "LancamentoRepository.findByFuncionarioId"
			, query="SELECT l FROM Lancamento l WHERE l.funcionario_id = :funcionarioId")
})
public interface LancamentoRepository extends JpaRepository <Lancamento, Long>{

	public Optional<List<Lancamento>> findByFuncionarioId(@Param ("funcionarioId") Long id);
	
	public Optional<Page<Lancamento>> findByFuncionarioId(@Param ("funcionarioId") Long id, Pageable pageable);
	
	public Optional<List<Lancamento>> findByFuncionarioIdAndData(@Param ("funcionarioId") Long id, @Param ("data") LocalDate data);
}
