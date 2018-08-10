package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import java.util.List;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;

public interface EmpresaService {

	Empresa findByCnpj(String cnpj);
	
	Empresa persist(Empresa empresa);
	
	Empresa update(Empresa empresa);
	
	Long deleteByCnpj(String cnpj);
	
	List<Empresa> findAll();
}
