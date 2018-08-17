package br.com.udemy.erikbagger.pontointeligente.api.domain.mapper;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;

import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.CadastroPFDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.domain.enums.PerfilEnum;
import br.com.udemy.erikbagger.pontointeligente.api.domain.util.PasswordUtils;

public class CadastroPFMapper {

	public static CadastroPFDto convertToDto(Funcionario entity) {
		ModelMapper mapper = new ModelMapper();
		CadastroPFDto dto = mapper.map(entity, CadastroPFDto.class);
		dto.setCnpj(entity.getEmpresa().getCnpj());
		return dto;
	}
	
	public static Funcionario convertToEntity(CadastroPFDto dto) {
		ModelMapper mapper = new ModelMapper();
		Funcionario entity = mapper.map(dto, Funcionario.class);
		entity.setPerfil(PerfilEnum.ROLE_USUARIO);
		entity.setSenha(new PasswordUtils().generatePassword(dto.getSenha()));
		dto.getValorHora().ifPresent(v -> entity.setValorHora(new BigDecimal(v)));
		dto.getQtdHorasDiarias().ifPresent(v -> entity.setQtdHorasDiarias(Float.parseFloat(v)));
		dto.getQtdHorasAlmoco().ifPresent(v -> entity.setQtdHorasAlmoco(Float.parseFloat(v)));
		
		return entity;
	}
}
