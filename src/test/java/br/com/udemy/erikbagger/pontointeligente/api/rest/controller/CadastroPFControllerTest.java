package br.com.udemy.erikbagger.pontointeligente.api.rest.controller;

import br.com.udemy.erikbagger.pontointeligente.api.PontoInteligenteApiApplicationTests;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.CadastroPFDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CadastroPFControllerTest extends PontoInteligenteApiApplicationTests {

    private static final String URI = "/cadastro-pf/";
    private static final String CPF = "35178098889";
    private static final String CNPJ = "90685584000179";
    private static final String NOME = "Tester";
    private static final String EMAIL = "tester@email.com";
    private static final String RAZAO_SOCIAL = "Penguin Formula Unipessoa Ltda.";
    private static final String SENHA = "12345678";

    @Test
    public void cadastrarCnpjNotFound() throws Exception {
        this.mockMvc.perform(post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequest()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.code").value("NAO ENCONTRADO"))
                .andExpect(jsonPath("$.error.message").value("Empresa nao encontrada para o CNPJ: " + CNPJ));
    }

    private String createRequest() throws JsonProcessingException {
        CadastroPFDto dto = new CadastroPFDto();
        dto.setNome(NOME);
        dto.setCnpj(CNPJ);
        dto.setEmail(EMAIL);
        dto.setSenha(SENHA);
        dto.setCpf(CPF);

        return mapper.writeValueAsString(dto);
    }
}