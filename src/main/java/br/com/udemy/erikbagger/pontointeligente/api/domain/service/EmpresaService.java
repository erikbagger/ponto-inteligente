package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;

public interface EmpresaService {

	Optional<Empresa> findByCnpj(String cnpj) throws NotFoundException;
	
	Empresa persist(Empresa empresa) throws NotFoundException, BadRequestException;
	
	Empresa update(Empresa empresa) throws NotFoundException;
	
	void deleteByCnpj(String cnpj) throws NotFoundException;
	
	List<Empresa> findAll() throws NotFoundException;
}
