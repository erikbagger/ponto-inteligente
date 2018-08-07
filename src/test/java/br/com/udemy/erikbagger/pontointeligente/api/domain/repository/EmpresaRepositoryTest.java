package br.com.udemy.erikbagger.pontointeligente.api.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;

public class EmpresaRepositoryTest extends PontoInteligenteApiApplicationTests {

	@Autowired
	protected EmpresaRepository repository;

	private static final String CNPJ = "00000000000011";

	@Test
	public void findByCnpjTest() {
		Empresa empresa = this.repository.findByCnpj(CNPJ);

		assertThat(empresa).isNotNull();
		assertThat(empresa.getId()).isNotNull();
		assertEquals(CNPJ, empresa.getCnpj());
	}

}