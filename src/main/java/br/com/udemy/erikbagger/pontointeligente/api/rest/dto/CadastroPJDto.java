package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroPJDto {

	private Long id;

	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 3, max = 200, message = "O nome deve conter entre 3 e 200 caracteres")
	private String nome;

	@NotEmpty(message = "Email não pode ser vazio")
	@Length(min = 3, max = 200, message = "O email deve conter entre 5 e 200 caracteres")
	@Email(message = "Formato de email inválido")
	private String email;

	@NotEmpty(message = "A senha não pode ser vazia")
	@Length(min = 8, max = 16, message = "A senha deve conter entre 8 e 16 caracteres")
	private String senha;

	@NotEmpty(message = "O CPF não pode ser vazio")
	@CPF(message = "Formato de CPF inválido")
	private String cpf;

	@NotEmpty(message = "A Razão Social não pode ser vazia")
	@Length(min = 5, max = 200, message = "O email deve conter entre 5 e 200 caracteres")
	private String razaoSocial;

	@NotEmpty(message = "O CNPJ não pode ser vazio")
	@CNPJ(message = "Formato de CNPJ inválido")
	private String cnpj;

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

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "CadastroPJDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + "]";
	}

}
