package br.com.udemy.erikbagger.pontointeligente.api.rest.controller;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmpresaControllerTest extends PontoInteligenteApiApplicationTests{

	private static final String URI = "/empresa/";
	
	private static final String CNPJ = "00000000000011";
	
	@Test
	public void findByCnpj() throws Exception {
		this.mockMvc.perform(get(URI + CNPJ)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.cnpj").value(CNPJ));
		
	}
	
	@Test
	public void findAll() throws Exception {
		this.mockMvc.perform(get(URI)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		
	}
}
