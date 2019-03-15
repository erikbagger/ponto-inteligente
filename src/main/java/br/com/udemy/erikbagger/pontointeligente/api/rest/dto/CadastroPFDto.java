package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class CadastroPFDto {

	private Long id;

	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 3, max = 200, message = "O nome deve conter entre 3 e 200 caracteres")
	private String nome;

	@NotEmpty(message = "Email não pode ser vazio")
	@Length(min = 3, max = 200, message = "O email deve conter entre 5 e 200 caracteres")
	@Email(message = "Formato de email inválido")
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotEmpty(message = "A senha não pode ser vazia")
	@Length(min = 8, max = 16, message = "A senha deve conter entre 8 e 16 caracteres")
	private String senha;

	@NotEmpty(message = "O CPF não pode ser vazio")
	@CPF(message = "Formato de CPF inválido")
	private String cpf;

	@NotEmpty(message = "O CNPJ não pode ser vazio")
	@CNPJ(message = "Formato de CNPJ inválido")
	private String cnpj;

	private Optional<String> valorHora = Optional.empty();
	private Optional<String> qtdHorasDiarias = Optional.empty();
	private Optional<String> qtdHorasAlmoco = Optional.empty();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Optional<String> getValorHora() {
		return valorHora;
	}

	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}

	public Optional<String> getQtdHorasDiarias() {
		return qtdHorasDiarias;
	}

	public void setQtdHorasDiarias(Optional<String> qtdHorasDiarias) {
		this.qtdHorasDiarias = qtdHorasDiarias;
	}

	public Optional<String> getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}

	public void setQtdHorasAlmoco(Optional<String> qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	@Override
	public String toString() {
		return "CadastroPFDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", cnpj=" + cnpj + ", valorHora=" + valorHora + ", qtdHorasDiarias=" + qtdHorasDiarias
				+ ", qtdHorasAlmoco=" + qtdHorasAlmoco + "]";
	}

}
