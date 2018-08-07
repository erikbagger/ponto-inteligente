package br.com.udemy.erikbagger.pontointeligente.api.domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.enums.PerfilEnum;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.FuncionarioService;

public class FuncionarioServiceTest extends PontoInteligenteApiApplicationTests {

	private static final String CPF = "12345678000";
	
	private static final String EMAIL = "email@email.com";
	
	private static final String NOVO_EMAIL = "novo_email@email.com";
	
	@Autowired
	private FuncionarioService service;

	@Test
	public void findByCpfTest() {
		Optional<Funcionario> funcionario = this.service.findByCpf(CPF);
		
		assertThat(funcionario).isNotNull();
		assertThat(funcionario.get().getId()).isNotNull();
		assertThat(funcionario.get().getId()).isEqualTo(10l);
		assertThat(funcionario.get().getCpf()).isEqualTo(CPF);
	}

	@Test
	public void findByEmail() {
		Optional<Funcionario> funcionario = this.service.findByEmail(EMAIL);
		
		assertThat(funcionario).isNotNull();
		assertThat(funcionario.get().getId()).isNotNull();
		assertThat(funcionario.get().getId()).isEqualTo(10l);
		assertThat(funcionario.get().getEmail()).isEqualTo(EMAIL);
	}

	@Test
	public void findByCpfOrEmail() {
		Optional<Funcionario> byCpf = this.service.findByCpfOrEmail(CPF, null);
		Optional<Funcionario> byEmail = this.service.findByCpfOrEmail(null, EMAIL);
		
		assertThat(byCpf).isNotNull();
		assertThat(byEmail).isNotNull();
		
		assertThat(byCpf.get().getId()).isNotNull();
		assertThat(byEmail.get().getId()).isNotNull();
		
		assertThat(byCpf.get().getCpf()).isEqualTo(CPF);
		assertThat(byEmail.get().getEmail()).isEqualTo(EMAIL);
		
		assertThat(byEmail.get().getId()).isEqualTo(byCpf.get().getId());
	}

	@Test
	public void persist() {
		Funcionario funcionario = createFuncionario();
		
		funcionario = this.service.persist(funcionario);
		
		assertThat(funcionario).isNotNull();
		assertThat(funcionario.getId()).isNotNull();
	}

	@Test
	public void update() {
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
	public void deleteByCpf() {
		
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
