package br.com.udemy.erikbagger.pontointeligente.api.service.impl;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Lancamento;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.repository.LancamentoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LancamentoServiceTest extends PontoInteligenteApiApplicationTests {

	@Autowired
	private LancamentoRepository repository;

	private static final Long ID = 10l;
	
	private static final PageRequest PAGEABLE = new PageRequest(0, 10, Direction.ASC, "id");

	@Test
	public void findByLancamentoIdTest() {
		Optional<List<Lancamento>> lancamentos = this.repository.findByFuncionarioId(ID);

		assertThat(lancamentos.get()).isNotEmpty();
	}

	@Test
	public void findByLancamentoIdPageableTest() {
		Optional<Page<Lancamento>> lancamentos = this.repository.findByFuncionarioId(ID, PAGEABLE);

		assertThat(lancamentos.get()).isNotEmpty();
	}

}
