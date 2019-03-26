package br.com.udemy.erikbagger.pontointeligente.api.persistence.mapper;

import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.EmpresaDto;
import org.modelmapper.ModelMapper;

public class EmpresaMapper {

	public static EmpresaDto convertToDto(Empresa entity) {
		return new ModelMapper().map(entity, EmpresaDto.class);
	}

	public static Empresa convertToEntity(EmpresaDto dto) {
		return new ModelMapper().map(dto, Empresa.class);
	}
}
