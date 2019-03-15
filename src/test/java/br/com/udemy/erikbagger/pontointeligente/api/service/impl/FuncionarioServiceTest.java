package br.com.udemy.erikbagger.pontointeligente.api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.enums.PerfilEnum;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.service.FuncionarioService;

public class FuncionarioServiceTest extends PontoInteligenteApiApplicationTests {

	private static final String CPF = "12345678000";

	private static final String EMAIL = "email@email.com";

	private static final String NOVO_EMAIL = "novo_email@email.com";

	@Autowired
	private FuncionarioService service;

	@Test
	public void findByCpfTest() {
		Funcionario funcionario = this.service.findByCpf(CPF).get();

		assertThat(funcionario).isNotNull();
		assertThat(funcionario.getId()).isNotNull();
		assertThat(funcionario.getId()).isEqualTo(10l);
		assertThat(funcionario.getCpf()).isEqualTo(CPF);
	}

	@Test
	public void findByEmail() {
		Funcionario funcionario = this.service.findByEmail(EMAIL).get();

		assertThat(funcionario).isNotNull();
		assertThat(funcionario.getId()).isNotNull();
		assertThat(funcionario.getId()).isEqualTo(10l);
		assertThat(funcionario.getEmail()).isEqualTo(EMAIL);
	}

	@Test
	public void findByCpfOrEmail() {
		Funcionario byCpf = this.service.findByCpfOrEmail(CPF, null).get();
		Funcionario byEmail = this.service.findByCpfOrEmail(null, EMAIL).get();

		assertThat(byCpf).isNotNull();
		assertThat(byEmail).isNotNull();

		assertThat(byCpf.getId()).isNotNull();
		assertThat(byEmail.getId()).isNotNull();

		assertThat(byCpf.getCpf()).isEqualTo(CPF);
		assertThat(byEmail.getEmail()).isEqualTo(EMAIL);

		assertThat(byEmail.getId()).isEqualTo(byCpf.getId());
	}

	@Test
	public void persist() throws BadRequestException {
		Funcionario funcionario = createFuncionario();

		funcionario = this.service.persist(funcionario);

		assertThat(funcionario).isNotNull();
		assertThat(funcionario.getId()).isNotNull();
	}

	@Test
	public void update() throws NotFoundException, BadRequestException {
		Funcionario funcionario = this.service.findByCpf(CPF).get();

		String oldEmail = funcionario.getEmail();
		Long id = funcionario.getId();

		funcionario.setEmail(NOVO_EMAIL);

		funcionario = this.service.update(funcionario);

		assertThat(funcionario).isNotNull();
		assertThat(funcionario.getId()).isNotNull();
		assertThat(funcionario.getId()).isEqualTo(id);
		assertThat(funcionario.getEmail()).isNotEqualTo(oldEmail);
		assertThat(funcionario.getEmail()).isEqualTo(NOVO_EMAIL);
	}

	@Test
	public void deleteByCpf() throws NotFoundException {
		this.service.deleteByCpf(CPF);
		
		Optional<Funcionario> funcionario = this.service.findByCpf(CPF);
		
		assertThat(!funcionario.isPresent());
	}

	private Funcionario createFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf("11122233344");
		funcionario.setNome("NOVO NOME");
		funcionario.setEmail("email@provedor.com");
		funcionario.setSenha("123456");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		return funcionario;
	}

}
