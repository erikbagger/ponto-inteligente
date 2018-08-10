package br.com.udemy.erikbagger.pontointeligente.api.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Lancamento;
import br.com.udemy.erikbagger.pontointeligente.api.domain.repository.LancamentoRepository;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService{

	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository repository;
	
	@Override
	public List<Lancamento> findByFuncionarioId(Long id) {
		log.info("Recebendo um id de Funcionario para buscar Lancamentos: {}", id);
		
		Optional<List<Lancamento>> lancamentos = this.repository.findByFuncionarioId(id);
		
		log.info("Retornando uma lista de Lancamentos: {}", lancamentos.toString());
		return lancamentos.get();
	}

	@Override
	public Page<Lancamento> findByFuncionarioId(Long id, Pageable pageable) {
		log.info("Recebendo um id de Funcionario para buscar Lancamentos (paginada): {}", id);
		
		Optional<Page<Lancamento>> lancamentos = this.repository.findByFuncionarioId(id, pageable);
		
		log.info("Retornando uma lista de Lancamentos: {}", lancamentos.toString());
		return lancamentos.get();
	}

}
