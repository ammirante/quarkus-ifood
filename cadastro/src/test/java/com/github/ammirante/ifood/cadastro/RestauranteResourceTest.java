package com.github.ammirante.ifood.cadastro;

import static io.restassured.RestAssured.given;

import java.util.Optional;

import javax.ws.rs.core.Response.Status;

import org.approvaltests.Approvals;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.github.ammirante.ifood.cadastro.dto.AtualizarRestauranteDto;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

/**
 * RestauranteResourceTest
 *
 */
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestauranteResourceTest {

	private static final Long ID_RESTAURANTE = 123L;
	
    /**
     * 
     */
    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testBuscarRestaurantes() {
    	String resultado = given()
    			.when()
    			.get("/restaurantes")
    			.then()
    			.statusCode(200)
    			.extract()
    			.asString()
    			;
    	Approvals.verifyJson(resultado);
    }
    
    /**
     * 
     */
    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testAtualizarRestaurante() {
    	AtualizarRestauranteDto atualizarRestauranteDto = new AtualizarRestauranteDto();
    	atualizarRestauranteDto.setNome("Novo nome");
    	given()
    	.with()
    	.contentType(ContentType.JSON)
    	.pathParam("id", ID_RESTAURANTE)
    	.body(atualizarRestauranteDto)
    	.when()
    	.put("/restaurantes/{id}")
    	.then()
    	.statusCode(Status.NO_CONTENT.getStatusCode())
    	.extract()
    	.asString()
    	;
    	
    	Restaurante restaurante = Restaurante.findById(ID_RESTAURANTE);
    	Assert.assertEquals(atualizarRestauranteDto.getNome(), restaurante.nome);
    }
    
    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testDeletarRestaurante() {
    	
    	given()
    	.with()
    	.pathParam("id", ID_RESTAURANTE)
    	.when()
    	.delete("/restaurantes/{id}")
    	.then()
    	.statusCode(Status.NO_CONTENT.getStatusCode())
    	.extract()
    	.asString()
    	;
    	
    	Optional<Restaurante> restaurante = Restaurante.findByIdOptional(ID_RESTAURANTE);
    	Assert.assertTrue(restaurante.isEmpty());
    }

}