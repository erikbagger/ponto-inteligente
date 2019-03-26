package br.com.udemy.erikbagger.pontointeligente.api.persistence.mapper;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.persistence.entity.Lancamento;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.LancamentoDto;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class LancamentoMapper {

	public static Lancamento convertToEntity(LancamentoDto dto) throws BadRequestException {
		ModelMapper mapper = new ModelMapper();
		Lancamento entity = mapper.map(dto, Lancamento.class);
		parseDate(entity, dto.getData());
		return entity;
	}
	
	public static LancamentoDto convertToDto(Lancamento entity) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(entity, LancamentoDto.class);
	}
	
	private static void parseDate(Lancamento entity, String data) throws BadRequestException {
		try {
			LocalDateTime date = LocalDateTime.parse(data);
			entity.setData(date);
		} catch (DateTimeParseException e) {
			throw new BadRequestException("Formato de data inválido", "A data deve estar no formato: yyyy-MM-ddThh:mm:ss");
		}
	}
}
