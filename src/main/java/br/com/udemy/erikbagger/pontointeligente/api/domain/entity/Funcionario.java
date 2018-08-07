package br.com.udemy.erikbagger.pontointeligente.api.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.udemy.erikbagger.pontointeligente.api.domain.enums.PerfilEnum;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -1985130705246888293L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNCIONARIO_SEQ")
	@SequenceGenerator(name = "FUNCIONARIO_SEQ", sequenceName = "FUNCIONARIO_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "SENHA", nullable = false)
	private String senha;

	@Column(name = "CPF", nullable = false)
	private String cpf;

	@Column(name = "VALOR_HORA", nullable = false)
	private BigDecimal valorHora;

	@Transient
	@Column(name = "QTD_HORAS_DIARIAS")
	private Float qtdHorasDiarias;

	@Transient
	@Column(name = "QTD_HORAS_ALMOCO")
	private Float qtdHorasAlmoco;

	@Enumerated(EnumType.STRING)
	@Column(name = "PERFIL")
	private PerfilEnum perfil;

	@Column(name = "DATA_CRIACAO", nullable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "DATA_ATUALIZACAO", nullable = false)
	private LocalDateTime dataAtualizacao;

	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa empresa;

	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Lancamento> lancamentos;

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

	public BigDecimal getValorHora() {
		return valorHora;
	}

	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	public Float getQtdHorasDiarias() {
		return qtdHorasDiarias;
	}

	public void setQtdHorasDiarias(Float qtdHorasDiarias) {
		this.qtdHorasDiarias = qtdHorasDiarias;
	}

	public Float getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}

	public void setQtdHorasAlmoco(Float qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Collection<Lancamento> getLancamentos() {
		return Collections.unmodifiableCollection(this.lancamentos);
	}

	public void addLancamento(Lancamento lancamento) {
		this.lancamentos.add(lancamento);
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
				"[id: %s, nome: %s, email: %s, senha: %s, cpf: %s, valorHora: %s, qtdHorasDiarias: %s, qtdHorasAlmoco: %s, perfil: %s, dataCriacao: %s, dataAtualizacao: %s, empresa: %s, lancamentos: %s, ]",
				this.id, this.email, this.senha, this.cpf, this.valorHora, this.qtdHorasDiarias, this.qtdHorasAlmoco,
				this.perfil, this.dataCriacao, this.dataAtualizacao, this.email, this.lancamentos);
	}

}
