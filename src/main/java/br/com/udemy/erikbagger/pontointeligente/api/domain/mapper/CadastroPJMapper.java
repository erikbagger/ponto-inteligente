package br.com.udemy.erikbagger.pontointeligente.api.domain.mapper;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;

import br.com.udemy.erikbagger.pontointeligente.api.domain.dto.CadastroPJDto;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.domain.entity.Funcionario;

public class CadastroPJMapper {

	public static CadastroPJDto convertToDto(Funcionario entity) {
		ModelMapper mapper = new ModelMapper();
		CadastroPJDto cadastroPJDto = mapper.map(entity, CadastroPJDto.class);
		cadastroPJDto.setCnpj(entity.getEmpresa().getCnpj());
		cadastroPJDto.setRazaoSocial(entity.getEmpresa().getRazaoSocial());
		return cadastroPJDto;
	}

	public static Map<String, Object> convertToEntity(CadastroPJDto dto) {
		ModelMapper mapper = new ModelMapper();
		Empresa empresa = mapper.map(dto, Empresa.class);
		Funcionario funcionario = mapper.map(dto, Funcionario.class);

		Map<String, Object> entities = new HashMap<>();
		entities.put("empresa", empresa);
		entities.put("funcionario", funcionario);

		return entities;
	}
}
