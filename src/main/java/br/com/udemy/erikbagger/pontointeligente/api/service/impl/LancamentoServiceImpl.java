package br.com.udemy.erikbagger.pontointeligente.api.service.impl;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Lancamento;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.repository.LancamentoRepository;
import br.com.udemy.erikbagger.pontointeligente.api.service.LancamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	@Autowired
	private LancamentoRepository repository;

	public Lancamento cadastrar(final Lancamento lancamento) {
		log.info("Recebendo um Lancamento para persistir: {}", lancamento);

		this.repository.save(lancamento);

		log.info("Retornando o Lancamento persistido: {}", lancamento);
		return lancamento;
	}

	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Recebendo um id para retornar um Lancamento: {}", id);

		Optional<Lancamento> lancamento = this.repository.findById(id);

		log.info("Retornando Lancamento: {}", lancamento);
		return lancamento;
	}

	@Override
	public Page<Lancamento> findByFuncionarioId(Long id, Pageable pageable) throws NotFoundException {
		log.info("Recebendo um id de Funcionario para buscar Lancamentos (paginada): {}", id);

		Page<Lancamento> lancamentos = this.repository.findByFuncionarioId(id, pageable)
				.orElseThrow(() -> new NotFoundException("Nenhum lancamento foi encontrado!"));

		log.info("Retornando uma lista de Lancamentos: {}", lancamentos.toString());
		return lancamentos;
	}

	@Override
	public void excluir(Long id) throws NotFoundException {
		log.info("Recebendo um id de Lancamento para excluir: {}", id);

		Lancamento lancamento = this.buscarPorId(id)
				.orElseThrow(() -> new NotFoundException("Lancamento não encontrado para o id:" + id));

		this.repository.delete(lancamento);

		log.info("Lancamento excluído com sucesso com o id: {}", id);
	}

	@Override
	public Lancamento atualizar(Lancamento source) throws BadRequestException {
		log.info("Recebendo um Lancamento para atualizar: {}", source);

		Lancamento target = this.buscarPorId(source.getId())
				.orElseThrow(() -> new BadRequestException("Lancamento não encontrado", "O Lancamento nao existe"));

		log.info("Retornando o Lancamento atualizado: {}", source);
		return target;
	}

}
