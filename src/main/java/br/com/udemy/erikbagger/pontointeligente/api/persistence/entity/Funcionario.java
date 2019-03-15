package br.com.udemy.erikbagger.pontointeligente.api.persistence.entity;

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

import br.com.udemy.erikbagger.pontointeligente.api.persistence.enums.PerfilEnum;

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

	@ManyToOne(fetch = FetchType.EAGER)
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

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", valorHora=" + valorHora + ", qtdHorasDiarias=" + qtdHorasDiarias + ", qtdHorasAlmoco="
				+ qtdHorasAlmoco + ", perfil=" + perfil + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
				+ dataAtualizacao + ", empresa=" + empresa + "]";
	}

}
