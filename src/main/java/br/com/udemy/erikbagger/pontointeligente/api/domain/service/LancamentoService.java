package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Lancamento;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;

public interface LancamentoService {
	
	public Lancamento cadastrar(Lancamento lancamento);
	
	public List<Lancamento> findByFuncionarioId(Long id);

	public Page<Lancamento> findByFuncionarioId(Long id, Pageable pageable) throws NotFoundException;
	
	public void excluir(Long id) throws NotFoundException;
	
	public Lancamento atualizar(Lancamento lancamento) throws BadRequestException;
}
