package br.com.udemy.erikbagger.pontointeligente.api.domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.EmpresaService;

public class EmpresaServiceTest extends PontoInteligenteApiApplicationTests {

	@Autowired
	protected EmpresaService service;

	private static final String CNPJ = "00000000000011";

	@Test
	public void findByCnpjTest() throws NotFoundException {
		Empresa empresa = service.findByCnpj(CNPJ).get();

		assertThat(empresa).isNotNull();
		assertThat(empresa.getId()).isNotNull();
		assertThat(empresa.getCnpj()).isEqualTo(CNPJ);
	}

	@Test
	public void persistTest() throws NotFoundException, BadRequestException {
		Empresa empresa = createEmpresa();

		empresa = this.service.persist(empresa);

		assertThat(empresa).isNotNull();
		assertThat(empresa.getId()).isNotNull();
		assertThat(empresa.getCnpj()).isEqualTo("00000000000012");
		assertThat(empresa.getRazaoSocial()).isEqualTo("BANCO X");
	}

	@Test
	public void updateTest() {
	}

	@Test
	public void deleteByCnpjTest() {
	}

	private Empresa createEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setCnpj("00000000000012");
		empresa.setRazaoSocial("BANCO X");
		return empresa;
	}
}
