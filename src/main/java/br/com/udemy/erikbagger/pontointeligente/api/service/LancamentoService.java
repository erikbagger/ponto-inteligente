package br.com.udemy.erikbagger.pontointeligente.api.service;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoService {
	
	public Lancamento cadastrar(Lancamento lancamento);

	public Page<Lancamento> findByFuncionarioId(Long id, Pageable pageable) throws NotFoundException;
	
	public void excluir(Long id) throws NotFoundException;
	
	public Lancamento atualizar(Lancamento lancamento) throws BadRequestException;
}
