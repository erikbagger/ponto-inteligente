package br.com.udemy.erikbagger.pontointeligente.api.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Funcionario;

public class FuncionarioRepositoryTest extends PontoInteligenteApiApplicationTests {

	@Autowired
	protected FuncionarioRepository repository;

	private static final String CPF = "12345678000";

	private static final String EMAIL = "email@email.com";

	@Test
	public void findByCpfTest() {
		Optional<Funcionario> funcionario = this.repository.findByCpf(CPF);

		assertThat(funcionario).isNotNull();
		assertThat(funcionario.get().getId()).isNotNull();
		assertThat(funcionario.get().getCpf()).isEqualTo(CPF);
	}

	@Test
	public void findByEmailTest() {
		Optional<Funcionario> funcionario = this.repository.findByEmail(EMAIL);

		assertThat(funcionario).isNotNull();
		assertThat(funcionario.get().getId()).isNotNull();
		assertThat(funcionario.get().getEmail()).isEqualTo(EMAIL);
	}

	@Test
	public void findByCpfOrEmailTest() {
		Optional<Funcionario> byCpf = this.repository.findByCpfOrEmail(CPF, null);

		assertThat(byCpf).isNotNull();
		assertThat(byCpf.get().getId()).isNotNull();
		assertThat(byCpf.get().getCpf()).isEqualTo(CPF);

		Optional<Funcionario> byEmail = this.repository.findByCpfOrEmail(null, EMAIL);

		assertThat(byEmail).isNotNull();
		assertThat(byEmail.get().getId()).isNotNull();
		assertThat(byEmail.get().getEmail()).isEqualTo(EMAIL);
	}
}
