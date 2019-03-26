package br.com.udemy.erikbagger.pontointeligente.api.persistence.repository;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

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
