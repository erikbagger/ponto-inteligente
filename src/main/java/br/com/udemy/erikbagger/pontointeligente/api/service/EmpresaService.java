package br.com.udemy.erikbagger.pontointeligente.api.service;

import java.util.List;
import java.util.Optional;

import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;

public interface EmpresaService {

	Optional<Empresa> findByCnpj(String cnpj) throws NotFoundException;
	
	Empresa persist(Empresa empresa) throws NotFoundException, BadRequestException;
	
	void deleteByCnpj(String cnpj) throws NotFoundException;
	
	List<Empresa> findAll() throws NotFoundException;
}
