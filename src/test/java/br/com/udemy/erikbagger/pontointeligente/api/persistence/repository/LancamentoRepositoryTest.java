package br.com.udemy.erikbagger.pontointeligente.api.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Lancamento;

public class LancamentoRepositoryTest extends PontoInteligenteApiApplicationTests {

	@Autowired
	private LancamentoRepository repository;

	private static final Long ID = 10l;

	@Test
	public void findByFuncionarioIdTest() {
		Optional<List<Lancamento>> lancamentos = this.repository.findByFuncionarioId(ID);

		assertThat(lancamentos.get()).isNotEmpty();
	}

	@Test
	public void findByFuncionarioIdTestPageable() {
		PageRequest request = new PageRequest(0, 4, Direction.ASC, "id");
		Optional<Page<Lancamento>> lancamentos = this.repository.findByFuncionarioId(ID, request);

		assertThat(lancamentos.get()).isNotNull();
		assertThat(lancamentos.get()).isNotEmpty();
		assertThat(lancamentos.get()).hasSize(4);
	}
}
