package com.github.ammirante.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.ammirante.ifood.cadastro.Restaurante;

/**
 * RestauranteMapper
 *
 */
@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

	/**
	 * @param dto
	 * @return
	 */
	@Mapping(target = "nome", source = "nomeFantasia")
	public Restaurante toRestaurante(AdicionarRestauranteDTO dto);
	
}
