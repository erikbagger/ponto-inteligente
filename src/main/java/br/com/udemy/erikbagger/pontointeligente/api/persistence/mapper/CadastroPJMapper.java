package br.com.udemy.erikbagger.pontointeligente.api.persistence.mapper;

import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Empresa;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Funcionario;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.enums.PerfilEnum;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.util.PasswordUtils;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.CadastroPJDto;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.Map;

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
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.generatePassword(dto.getSenha()));

		Map<String, Object> entities = new HashMap<>();
		entities.put("empresa", empresa);
		entities.put("funcionario", funcionario);

		return entities;
	}
}
