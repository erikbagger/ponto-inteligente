package br.com.udemy.erikbagger.pontointeligente.api.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Lancamento;

public interface LancamentoService {

	public List<Lancamento> findByFuncionarioId(Long id);

	public Page<Lancamento> findByFuncionarioId(Long id, Pageable pageable);
}
