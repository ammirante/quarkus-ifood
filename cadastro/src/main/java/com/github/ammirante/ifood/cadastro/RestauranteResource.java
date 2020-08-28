package com.github.ammirante.ifood.cadastro;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.github.ammirante.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.github.ammirante.ifood.cadastro.dto.RestauranteMapper;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "restaurante")
public class RestauranteResource {
	
	@Inject
	private RestauranteMapper restauranteMapper;

    /**
     * @return
     */
    @GET
    public List<Restaurante> buscarRestaurantes() {
        return Restaurante.listAll();
    }
    
    /**
     * @param restauranteDto
     * @return
     */
    @POST
    @Transactional
    public Response adicionarRestaurante(AdicionarRestauranteDTO restauranteDto) {
    	Restaurante restaurante = restauranteMapper.toRestaurante(restauranteDto);
    	restaurante.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    /**
     * @param idRestaurante
     * @param restauranteDto
     */
    @PUT
    @Path("{id}")
    @Transactional
    public void atualizarRestaurante(@PathParam("id") Long idRestaurante, Restaurante restauranteDto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException();
    	}
    	
    	Restaurante restaurante = restauranteOp.get();
    	restaurante.nome = restauranteDto.nome;
    	restaurante.persist();
    }
    
    /**
     * @param idRestaurante
     */
    @DELETE
    @Path("{id}")
    @Transactional
    public void deletarRestaurante(@PathParam("id") Long idRestaurante) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	
    	restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
    		throw new NotFoundException();
    	});
    }
    
    /**
     * @param idRestaurante
     * @return
     */
    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name = "prato")
    public List<Prato> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante informado não existe");
    	}
    	
    	return Prato.list("restaurante", restauranteOp.get());
    }
    
    /**
     * @param idRestaurante
     * @param pratoDto
     * @return
     */
    @POST
    @Tag(name = "prato")
    @Path("{idRestaurante}/pratos")
    @Transactional
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato pratoDto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante informado não existe");
    	}
    	pratoDto.restaurante = restauranteOp.get();
    	pratoDto.persist();
    	
    	return Response.status(Status.CREATED).build();
    }
    
    /**
     * @param idRestaurante
     * @param idPrato
     * @param pratoDto
     */
    @PUT
    @Tag(name = "prato")
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    public void atualizarPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long idPrato, Prato pratoDto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante informado não existe");
    	}
    	
    	Optional<Prato> pratoOp = Prato.findByIdOptional(idPrato);
    	
    	if(pratoOp.isEmpty()) {
    		throw new NotFoundException("Prato informado não existe");
    	}
    	
    	Prato prato = pratoOp.get();
    	prato.descricao = pratoDto.descricao;
    	prato.nome = pratoDto.nome;
    	prato.preco = pratoDto.preco;
    	prato.persist();
    }
    
    /**
     * @param idRestaurante
     * @param idPrato
     */
    @DELETE
    @Tag(name = "prato")
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    public void deletarPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long idPrato) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante informado não existe");
    	}
    	
    	Optional<Prato> pratoOp = Prato.findByIdOptional(idPrato);
    	pratoOp.ifPresentOrElse(Prato::delete, () -> {
    		throw new NotFoundException();
    	});
    }
}