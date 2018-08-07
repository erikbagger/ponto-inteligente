package br.com.udemy.erikbagger.pontointeligente.api.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;

public class FuncionarioRepositoryTest extends PontoInteligenteApiApplicationTests {

	@Autowired
	protected FuncionarioRepository repository;

	private static final String CPF = "12345678000";

	private static final String EMAIL = "email@email.com";

	@Test
	public void findByCpfTest() {
		Funcionario funcionario = this.repository.findByCpf(CPF);

		assertThat(funcionario).isNotNull();
		assertThat(funcionario.getId()).isNotNull();
		assertThat(funcionario.getCpf()).isEqualTo(CPF);
	}

	@Test
	public void findByEmailTest() {
		Funcionario funcionario = this.repository.findByEmail(EMAIL);

		assertThat(funcionario).isNotNull();
		assertThat(funcionario.getId()).isNotNull();
		assertThat(funcionario.getEmail()).isEqualTo(EMAIL);
	}

	@Test
	public void findByCpfOrEmailTest() {
		Funcionario byCpf = this.repository.findByCpfOrEmail(CPF, null);

		assertThat(byCpf).isNotNull();
		assertThat(byCpf.getId()).isNotNull();
		assertThat(byCpf.getCpf()).isEqualTo(CPF);

		Funcionario byEmail = this.repository.findByCpfOrEmail(null, EMAIL);

		assertThat(byEmail).isNotNull();
		assertThat(byEmail.getId()).isNotNull();
		assertThat(byEmail.getEmail()).isEqualTo(EMAIL);
	}
}
