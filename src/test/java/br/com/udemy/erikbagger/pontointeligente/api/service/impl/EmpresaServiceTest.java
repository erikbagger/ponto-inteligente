package br.com.udemy.erikbagger.pontointeligente.api.service.impl;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.service.EmpresaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EmpresaServiceTest extends PontoInteligenteApiApplicationTests {

	@Autowired
	protected EmpresaService service;

	private static final String CNPJ = "00000000000011"

	@Test
	public void findByCnpjTest() {
		Empresa empresa = service.findByCnpj(CNPJ).get();

		assertThat(empresa).isNotNull();
		assertThat(empresa.getId()).isNotNull();
		assertThat(empresa.getCnpj()).isEqualTo(CNPJ);
	}

	@Test
	public void findByCnpjNotFoundTest() {
		final String randomCnpj = "12345678910123";
		Optional<Empresa> empresa = service.findByCnpj(randomCnpj);

		assertThat(!empresa.isPresent());
	}

	@Test
	public void persistTest() throws BadRequestException {
		Empresa empresa = createEmpresa();

		empresa = this.service.persist(empresa);

		assertThat(empresa).isNotNull();
		assertThat(empresa.getId()).isNotNull();
		assertThat(empresa.getCnpj()).isEqualTo("00000000000012");
		assertThat(empresa.getRazaoSocial()).isEqualTo("BANCO X");
	}

	@Test(expected = BadRequestException.class)
	public void persistEmpresaAlreadyExistsTest() throws BadRequestException {
		Empresa empresa = createEmpresa();

		empresa = this.service.persist(empresa);

		assertThat(empresa).isNotNull();

		empresa.setRazaoSocial("Outra razao social");
		this.service.persist(empresa);
	}

	@Test
	public void deleteByCnpjTest() throws NotFoundException{
		this.service.deleteByCnpj(CNPJ);

		Optional<Empresa> empresa = this.service.findByCnpj(CNPJ);

		assertThat(!empresa.isPresent());
	}

	@Test(expected = NotFoundException.class)
	public void deleteByCnpjEmpresaDoesNotExistTest() throws NotFoundException{
		final String randomCnpj = "12345678910123";

		this.service.deleteByCnpj(randomCnpj);
	}

	private Empresa createEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setCnpj("00000000000012");
		empresa.setRazaoSocial("BANCO X");
		return empresa;
	}
}
