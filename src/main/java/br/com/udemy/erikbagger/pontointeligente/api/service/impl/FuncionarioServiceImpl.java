package br.com.udemy.erikbagger.pontointeligente.api.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.repository.FuncionarioRepository;
import br.com.udemy.erikbagger.pontointeligente.api.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

	private final FuncionarioRepository repository;
	
	public FuncionarioServiceImpl(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	public Optional<Funcionario> buscarPorId(Long id){
		log.info("Recebendo um id para retornar um Funcionario: {}", id);
		
		Optional<Funcionario> funcionario = Optional.ofNullable(this.repository.findOne(id));
		
		log.info("Retornando um Funcionario: {}", funcionario);
		return funcionario;
	}
	
	@Override
	public List<Funcionario> listar() throws NotFoundException {
		log.info("Buscando uma lista de Funcionario");
		
		List<Funcionario> lista = this.repository.findAll();
		
		if(lista.isEmpty()) {
			log.error("Nenhum registro encontrado");
			throw new NotFoundException("Nenhum registro encontrado!");
		}
		
		log.info("Retornando uma lista de Funcionarios: {}", lista);
		return lista;
	}

	@Override
	public Optional<Funcionario> findByCpf(String cpf) {
		log.info("Recebendo um CPF para efetuar a busca por um Funcionario: {}", cpf);

		Optional<Funcionario> funcionario = this.repository.findByCpf(cpf);

		log.info("Retornando um objeto Funcionario: {}", funcionario);
		return funcionario;
	}

	@Override
	public Optional<Funcionario> findByEmail(String email) {
		log.info("Recebendo um email para efetuar a busca por um Funcionario: {}", email);

		Optional<Funcionario> funcionario = this.repository.findByEmail(email);

		log.info("Retornando um objeto Funcionario: {}", funcionario);
		return funcionario;
	}

	@Override
	public Optional<Funcionario> findByCpfOrEmail(String cpf, String email) {
		log.info("Recebendo um CPF ou um email para efetuar a busca por um Funcionario: [cpf = {}, email = {}]", cpf,
				email);

		Optional<Funcionario> funcionario = this.repository.findByCpfOrEmail(cpf, email);

		log.info("Retornando um objeto Funcionario: {}", funcionario);
		return funcionario;
	}

	@Override
	public Funcionario persist(Funcionario entity) throws BadRequestException {
		log.info("Recebendo um Funcionario para persistir: {}", entity.toString());

		Optional<Funcionario> funcionario = this.findByCpfOrEmail(entity.getCpf(), entity.getEmail());
		
		if(funcionario.isPresent()) {
			log.error("Encontrado um registro com o CPF ou email: {}", funcionario);
			throw new BadRequestException("ERRO", "Funcionario já cadastrado. Verifique o CPF e o email");
		}
		
		entity = this.repository.save(entity);

		log.info("Retornando um objeto Funcionario: {}", entity);
		return entity;
	}

	@Override
	public Funcionario update(Funcionario entity) throws NotFoundException, BadRequestException {
		log.info("Recebendo objeto Funcionario para atualizar: {}", entity);
		
		Funcionario funcionario = Optional.ofNullable(this.repository.findOne(entity.getId())).orElseThrow(() -> new NotFoundException("Erro ao atualizar. Registro não encontrado"));
		
		if(!funcionario.getEmail().equals(entity.getEmail())) {
			Optional<Funcionario> email = this.findByEmail(entity.getEmail());
			if(email.isPresent()) {
				log.error("Encontrado um registro com o email");
				throw new BadRequestException("EMAIL JÁ EXISTENTE", "Já existe um registro com esse email!");
			}
		}
		
		updateAttributes(entity, funcionario);
		funcionario = this.repository.save(funcionario);

		log.info("Retornando um objeto Funcionario: {}", entity);
		return entity;
	}

	@Override
	public void deleteByCpf(String cpf) throws NotFoundException {
		log.info("Recebendo um CPF para remover um Funcionario: {}", cpf);

		Funcionario funcionario = this.repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Registro não encontrado"));

		Long id = funcionario.getId();

		this.repository.delete(id);
		log.info("Objeto Funcionario removido com sucesso com o id: {}, e CPF: {}", id, cpf);
	}
	
	private void updateAttributes(Funcionario source, Funcionario target) {
		String senha = source.getSenha();
		BigDecimal valorHora = source.getValorHora();
		Float qtdHorasDiarias = source.getQtdHorasDiarias();
		Float qtdHorasAlmoco = source.getQtdHorasAlmoco();
		
		target.setNome(source.getNome());
		target.setEmail(source.getEmail());
		
		if (null != senha) 
			target.setSenha(senha);
		if (null != valorHora)
			target.setValorHora(valorHora);
		if (null != qtdHorasDiarias)
			target.setQtdHorasDiarias(qtdHorasDiarias);
		if (null != qtdHorasAlmoco)
			target.setQtdHorasAlmoco(qtdHorasAlmoco);
	}
	
}
