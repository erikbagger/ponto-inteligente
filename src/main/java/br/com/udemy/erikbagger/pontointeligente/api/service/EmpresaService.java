package br.com.udemy.erikbagger.pontointeligente.api.service;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {

	Optional<Empresa> findByCnpj(String cnpj);
	
	Empresa persist(Empresa empresa) throws BadRequestException;
	
	void deleteByCnpj(String cnpj) throws NotFoundException;
	
	List<Empresa> findAll() throws NotFoundException;
}
