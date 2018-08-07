package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import java.util.Optional;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;

public interface EmpresaService {

	Optional<Empresa> findByCnpj(String cnpj);
	
	Empresa persist(Empresa empresa);
	
	Empresa update(Empresa empresa);
	
	void deleteByCnpj(String cnpj);
}
