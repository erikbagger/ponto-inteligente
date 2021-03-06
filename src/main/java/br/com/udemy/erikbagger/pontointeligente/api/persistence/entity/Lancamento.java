package br.com.udemy.erikbagger.pontointeligente.api.persistence.entity;

import br.com.udemy.erikbagger.pontointeligente.api.persistence.enums.LancamentoEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "LANCAMENTO")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = -3559253436239994094L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANCAMENTO_SEQ")
	@SequenceGenerator(name = "LANCAMENTO_SEQ", sequenceName = "lancamento_id_seq", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DATA", nullable = false)
	private LocalDateTime data;

	@Column(name = "DESCRICAO", nullable = true)
	private String descricao;

	@Column(name = "LOCALIZACAO", nullable = true)
	private String localizacao;

	@Column(name = "DATA_CRIACAO", nullable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "DATA_ATUALIZACAO", nullable = false)
	private LocalDateTime dataAtualizacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_LANCAMENTO", nullable = false)
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

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", data=" + data + ", descricao=" + descricao + ", localizacao=" + localizacao
				+ ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", tipoLancamento="
				+ tipoLancamento + "]";
	}

}
