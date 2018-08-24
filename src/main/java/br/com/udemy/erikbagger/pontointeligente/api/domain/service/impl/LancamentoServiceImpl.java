package br.com.udemy.erikbagger.pontointeligente.api.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Lancamento;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.domain.repository.LancamentoRepository;
import br.com.udemy.erikbagger.pontointeligente.api.domain.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	@Autowired
	private LancamentoRepository repository;

	public Lancamento cadastrar(final Lancamento lancamento) {
		log.info("Recebendo um Lancamento para persistir: {}", lancamento);

//		Optional<List<Lancamento>> lancamentos = this.repository.findByFuncionarioIdAndData(lancamento.getId(),
//				lancamento.getData().toLocalDate());
//		Supplier<Stream<Lancamento>> supplier = () -> lancamentos.isPresent() ? lancamentos.get().stream()
//				: Stream.empty();
//
//		Optional<Boolean> found = Optional.ofNullable(
//				Boolean.valueOf(supplier.get().anyMatch(l -> l.getTipoLancamento() == lancamento.getTipoLancamento())));
//
//		if(found.isPresent()) {
//			
//		}
		this.repository.save(lancamento);

		log.info("Retornando o Lancamento persistido: {}", lancamento);
		return lancamento;
	}

	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Recebendo um id para retornar um Lancamento: {}", id);

		Optional<Lancamento> lancamento = Optional.ofNullable(this.repository.findOne(id));

		log.info("Retornando Lancamento: {}", lancamento);
		return lancamento;
	}

	@Override
	public List<Lancamento> findByFuncionarioId(Long id) {
		log.info("Recebendo um id de Funcionario para buscar Lancamentos: {}", id);

		Optional<List<Lancamento>> lancamentos = this.repository.findByFuncionarioId(id);

		log.info("Retornando uma lista de Lancamentos: {}", lancamentos.toString());
		return lancamentos.get();
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
