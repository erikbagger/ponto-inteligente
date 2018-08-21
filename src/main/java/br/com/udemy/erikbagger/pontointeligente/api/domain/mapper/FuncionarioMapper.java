package br.com.udemy.erikbagger.pontointeligente.api.domain.mapper;

import java.math.BigDecimal;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.FuncionarioDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.enums.PerfilEnum;
import br.com.udemy.erikbagger.pontointeligente.api.domain.util.PasswordUtils;

public class FuncionarioMapper {

	public static FuncionarioDto convertToDto(Funcionario entity) {
		ModelMapper mapper = new ModelMapper();
		FuncionarioDto dto = mapper.map(entity, FuncionarioDto.class);
		dto.setValorHora(Optional.ofNullable(String.valueOf(entity.getValorHora())));
		dto.setQtdHorasDiarias((Optional.ofNullable(String.valueOf(entity.getValorHora()))));
		dto.setQtdHorasAlmoco((Optional.ofNullable(String.valueOf(entity.getValorHora()))));
		return dto;
	}
	
	public static Funcionario convertToEntity(FuncionarioDto dto) {
		Funcionario entity = new Funcionario();
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		
		dto.getSenha().ifPresent(v -> entity.setSenha(PasswordUtils.generatePassword(dto.getSenha().get())));
		dto.getValorHora().ifPresent(v -> entity.setValorHora(new BigDecimal(v)));
		dto.getQtdHorasDiarias().ifPresent(v -> entity.setQtdHorasDiarias(Float.parseFloat(v)));
		dto.getQtdHorasAlmoco().ifPresent(v -> entity.setQtdHorasAlmoco(Float.parseFloat(v)));
		
		entity.setPerfil(PerfilEnum.ROLE_USUARIO);
		return entity;
	}
}
