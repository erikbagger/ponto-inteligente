package br.com.udemy.erikbagger.pontointeligente.api.domain.dto;

public class EmpresaDto {

	private Long id;
	private String cnpj;
	private String razaoSocial;

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

	public String toString() {
		return String.format("Empresa: {id: %s, cnpj: %s, razaoSocial: %s}", this.id, this.cnpj, this.razaoSocial);
	}
}