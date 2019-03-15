package br.com.udemy.erikbagger.pontointeligente.api.persistence.mapper;

import org.modelmapper.ModelMapper;

import br.com.udemy.erikbagger.pontointeligente.api.rest.controller.dto.EmpresaDto;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;

public class EmpresaMapper {

	public static EmpresaDto convertToDto(Empresa entity) {
		return new ModelMapper().map(entity, EmpresaDto.class);
	}

	public static Empresa convertToEntity(EmpresaDto dto) {
		return new ModelMapper().map(dto, Empresa.class);
	}
}
