package br.com.udemy.erikbagger.pontointeligente.api.domain.controller.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.CadastroPJDto;

public class CadastroPJControllerTest extends PontoInteligenteApiApplicationTests{

	private static final String URI = "/cadastro-pj/";
	private static final String CPF = "35178098889";
	private static final String CNPJ = "90685584000179";
	private static final String NOME = "Tester";
	private static final String EMAIL = "tester@email.com";
	private static final String RAZAO_SOCIAL = "Penguin Formula Unipessoa Ltda.";
	private static final String SENHA = "12345678";
	
	@Test
	public void cadastrarTest() throws UnsupportedEncodingException, Exception {
		this.mockMvc.perform(post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(createRequest()))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.cnpj").value(CNPJ))
				.andExpect(jsonPath("$.nome").value(NOME))
				.andExpect(jsonPath("$.email").value(EMAIL))
				.andExpect(jsonPath("$.cpf").value(CPF))
				.andReturn().getResponse().getContentAsString();;
	}
	
	private String createRequest() throws JsonProcessingException {
		CadastroPJDto dto = new CadastroPJDto();
		dto.setNome(NOME);
		dto.setRazaoSocial(RAZAO_SOCIAL);
		dto.setCnpj(CNPJ);
		dto.setEmail(EMAIL);
		dto.setSenha(SENHA);
		dto.setCpf(CPF);
		
		return mapper.writeValueAsString(dto);
	}
}
