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

@Sql(scripts = "startup_empresa.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class EmpresaRepositoryTest extends PontoInteligenteApiApplicationTests {

	private static final String CNPJ = "00000000000011";

	@Autowired
	private EmpresaRepository repository;

	@Test
	public void findByCnpjTest() {
		Empresa empresa = this.repository.findByCnpj(CNPJ);

		assertThat(empresa).isNotNull();
		assertNotNull(empresa.getId());
		assertTrue(empresa.getId() == 1l);
		assertEquals(CNPJ, empresa.getCnpj());
	}
}
