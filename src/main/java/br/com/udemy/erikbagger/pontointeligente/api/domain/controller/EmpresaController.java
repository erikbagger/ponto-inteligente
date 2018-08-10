package br.com.udemy.erikbagger.pontointeligente.api.domain.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;

@RestController
@RequestMapping("/empresa")
public interface EmpresaController {

	@PostMapping
	ResponseEntity<Empresa> cadastrar(Empresa empresa, BindingResult result);

	@GetMapping("{cnpj}")
	ResponseEntity<Empresa> buscarPorCnpj(String cnpj);

	@PutMapping
	ResponseEntity<Empresa> atualizar(Empresa empresa);

	@DeleteMapping("{cnpj}")
	ResponseEntity<String> excluir(String cnpj);
	
	@GetMapping
	ResponseEntity<List<Empresa>> listar();
}
