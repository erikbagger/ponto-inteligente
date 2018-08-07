package br.com.udemy.erikbagger.pontointeligente.api.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 8551144249006804173L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cnpj", nullable = false)
	private String cnpj;

	@Column(name = "razao_social", nullable = false)
	private String razaoSocial;

	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "data_atualizacao", nullable = false)
	private LocalDateTime dataAtualizacao;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Funcionario> funcionarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Collection<Funcionario> getFuncionarios() {
		return Collections.unmodifiableCollection(this.funcionarios);
	}

	public void addFuncionarios(Funcionario funcionario) {
		this.funcionarios.add(funcionario);
	}

	@PrePersist
	public void prePersist() {
		LocalDateTime dataAtual = LocalDateTime.now();
		this.dataCriacao = dataAtual;
		this.dataAtualizacao = dataAtual;
	}

	@PreUpdate
	public void preUpdate() {
		LocalDateTime dataAtual = LocalDateTime.now();
		this.dataAtualizacao = dataAtual;
	}

	public String toString() {
		return String.format(
				"[id: %s, razaoSocial: %s, cnpj: %s, dataCriacao: %s, dataAtualizacao: %s, funcionarios: %s]", this.id,
				this.razaoSocial, this.cnpj, this.dataCriacao, this.dataAtualizacao, this.funcionarios);
	}
}
