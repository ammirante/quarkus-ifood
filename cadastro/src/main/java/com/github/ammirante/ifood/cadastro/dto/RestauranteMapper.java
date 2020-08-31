package com.github.ammirante.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.github.ammirante.ifood.cadastro.entidade.Restaurante;

/**
 * RestauranteMapper
 *
 */
@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mapping(target = "nome", source = "nomeFantasia")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "localizacao.id", ignore = true)
    public Restaurante toRestaurante(AdicionarRestauranteDTO dt);
	
	/**
	 * @param dto
	 * @param restaurante
	 * @return
	 */
	@Mapping(target = "nome", source = "nomeFantasia")
	public Restaurante toRestaurante(AtualizarRestauranteDto dto, @MappingTarget Restaurante restaurante);
	/**
	 * @param restaurante
	 * @return
	 */
	@Mapping(target = "dataCriacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
	public RestauranteDTO toRestauranteDTO(Restaurante restaurante);
}
