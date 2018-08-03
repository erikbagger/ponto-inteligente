package br.com.udemy.erikbagger.pontointeligente.api.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.udemy.erikbagger.pontointeligente.api.domain.enums.LancamentoEnum;

@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = -3559253436239994094L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data", nullable = false)
	private LocalDateTime data;

	@Column(name = "descricao", nullable = true)
	private String descricao;

	@Column(name = "localizacao", nullable = true)
	private String localizacao;

	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "data_atualizacao", nullable = false)
	private LocalDateTime dataAtualizacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_lancamento", nullable = false)
	private LancamentoEnum tipoLancamento;

	@ManyToOne(fetch = FetchType.LAZY)
	private Funcionario funcionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
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

	public LancamentoEnum getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(LancamentoEnum tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
				"[id: %s, data: %s, descricao: %s, localizacao: %s, dataCriacao: %s, dataAtualizacao: %s, tipoLancamento: %s, funcionario: %s]",
				this.id, this.data, this.descricao, this.localizacao, this.dataCriacao, this.dataAtualizacao,
				this.tipoLancamento, this.funcionario);
	}
}
