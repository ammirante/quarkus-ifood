package com.github.ammirante.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.github.ammirante.ifood.cadastro.entidade.Prato;

/**
 * PratoMapper
 *
 */
@Mapper(componentModel = "cdi")
public interface PratoMapper {

	/**
	 * @param dto
	 * @return
	 */
	public Prato toPrato(AdicionarPratoDto dto);
	
	/**
	 * @param dto
	 * @param prato
	 */
	public void toPrato(AtualizarPratoDto dto, @MappingTarget Prato prato);
	
	/**
	 * @param prato
	 * @return
	 */
	@Mapping(source = "restaurante", target = "restaurante")
	public PratoDTO toPratoDTO(Prato prato);
	
}
