package br.com.udemy.erikbagger.pontointeligente.api.persistence.mapper;

import java.math.BigDecimal;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto.CadastroPFDto;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.enums.PerfilEnum;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.util.PasswordUtils;

public class CadastroPFMapper {

	public static CadastroPFDto convertToDto(Funcionario entity) {
		ModelMapper mapper = new ModelMapper();
		CadastroPFDto dto = mapper.map(entity, CadastroPFDto.class);
		dto.setCnpj(entity.getEmpresa().getCnpj());
		dto.setValorHora(Optional.ofNullable(String.valueOf(entity.getValorHora())));
		dto.setQtdHorasDiarias((Optional.ofNullable(String.valueOf(entity.getValorHora()))));
		dto.setQtdHorasAlmoco((Optional.ofNullable(String.valueOf(entity.getValorHora()))));
		return dto;
	}
	
	public static Funcionario convertToEntity(CadastroPFDto dto) {
		Funcionario entity = new Funcionario();
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setSenha(PasswordUtils.generatePassword(dto.getSenha()));
		entity.setCpf(dto.getCpf());
		
		dto.getValorHora().ifPresent(v -> entity.setValorHora(new BigDecimal(v)));
		dto.getQtdHorasDiarias().ifPresent(v -> entity.setQtdHorasDiarias(Float.parseFloat(v)));
		dto.getQtdHorasAlmoco().ifPresent(v -> entity.setQtdHorasAlmoco(Float.parseFloat(v)));
		
		entity.setPerfil(PerfilEnum.ROLE_USUARIO);
		return entity;
	}
	
}
