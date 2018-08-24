package br.com.udemy.erikbagger.pontointeligente.api.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;

public class EmpresaRepositoryTest extends PontoInteligenteApiApplicationTests {

	@Autowired
	protected EmpresaRepository repository;

	private static final String CNPJ = "00000000000011";

	@Test
	public void findByCnpjTest() {
		Optional<Empresa> empresa = this.repository.findByCnpj(CNPJ);

		assertThat(empresa).isNotNull();
		assertThat(empresa.get().getId()).isNotNull();
		assertEquals(CNPJ, empresa.get().getCnpj());
	}
}
