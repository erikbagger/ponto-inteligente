package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;

public interface EmpresaService {

	Empresa findByCnpj(String cnpj);
	
	Empresa persist(Empresa empresa);
	
	Empresa update(Empresa empresa);
	
	void deleteByCnpj(String cnpj);
}
