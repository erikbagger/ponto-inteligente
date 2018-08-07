package br.com.udemy.erikbagger.pontointeligente.api.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Lancamento;

public class LancamentoRepositoryTest extends PontoInteligenteApiApplicationTests{

	@Autowired
	private LancamentoRepository repository;
	
	private static final Long ID = 1l;
	
	@Test
	public void findByFuncionarioIdTest() {
		List<Lancamento> lancamentos = this.repository.findByFuncionarioId(ID);
		
		assertThat(lancamentos).isNotEmpty();
	}
	
	@Test
	public void findByFuncionarioIdTestPageable() {
		PageRequest request = new PageRequest(0, 4, Direction.ASC, "id");
		Page<Lancamento> lancamentos = this.repository.findByFuncionarioId(ID, request);
		
		assertThat(lancamentos).isNotNull();
		assertThat(lancamentos).isNotEmpty();
		assertThat(lancamentos).hasSize(4);
	}
}
