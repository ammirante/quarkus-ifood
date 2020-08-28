package com.github.ammirante.ifood.cadastro;

import java.util.Optional;

import javax.ws.rs.core.Response.Status;

import org.approvaltests.Approvals;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.github.ammirante.ifood.cadastro.dto.AtualizarPratoDto;
import com.github.ammirante.ifood.cadastro.dto.AtualizarRestauranteDto;
import com.github.ammirante.ifood.cadastro.entidade.Prato;
import com.github.ammirante.ifood.cadastro.entidade.Restaurante;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

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
	private static final Long ID_PRATO = 123L;
	
	private RequestSpecification given() {
		return RestAssured.given().contentType(ContentType.JSON);
	}
	
    /**
     * 
     */
    @Test
    @DataSet(value = "restaurantes-cenario-1.yml")
    public void testBuscarRestaurantes() {
    	String resultado = given()
    			.when()
    			.get("/restaurantes")
    			.then()
    			.statusCode(Status.OK.getStatusCode())
    			.extract()
    			.asString()
    			;
    	Approvals.verifyJson(resultado);
    }
    
    /**
     * 
     */
    @Test
    @DataSet(value = "restaurantes-cenario-1.yml")
    public void testAtualizarRestaurante() {
    	AtualizarRestauranteDto atualizarRestauranteDto = new AtualizarRestauranteDto();
    	atualizarRestauranteDto.nome = "Novo nome";
    	given()
    	.with()
    	.pathParam("id", ID_RESTAURANTE)
    	.body(atualizarRestauranteDto)
    	.when()
    	.put("/restaurantes/{id}")
    	.then()
    	.statusCode(Status.NO_CONTENT.getStatusCode())
    	;
    	
    	Restaurante restaurante = Restaurante.findById(ID_RESTAURANTE);
    	Assert.assertEquals(atualizarRestauranteDto.nome, restaurante.nome);
    }
    
    @Test
    @DataSet(value = "restaurantes-cenario-1.yml", cleanAfter = true)    
    public void testDeletarRestaurante() {
    	given()
    	.with()
    	.pathParam("id", ID_RESTAURANTE)
    	.when()
    	.delete("/restaurantes/{id}")
    	.then()
    	.statusCode(Status.NO_CONTENT.getStatusCode())
    	;
    	
    	Optional<Restaurante> restaurante = Restaurante.findByIdOptional(ID_RESTAURANTE);
    	Assert.assertTrue(restaurante.isEmpty());
    }
    
    /**
     * 
     */
    @Test
    public void testAdicionarRestaurante() {
    	Restaurante restauranteInsercao = new Restaurante();
    	restauranteInsercao.nome = "Novo restaurante";
    	given()
    	.body(restauranteInsercao)
    	.when()
    	.post("/restaurantes")
    	.then()
    	.statusCode(Status.CREATED.getStatusCode())
    	;
    }
    
    /**
     * 
     */
    @Test
    @DataSet(value = "pratos-cenario-1.yml", cleanAfter = true)
    public void testBuscarPratos() {
    	String resultado = given()
    			.when()
    			.with()
    			.pathParam("idRestaurante", ID_RESTAURANTE)
    			.get("/restaurantes/{idRestaurante}/pratos")    			
    			.then()
    			.statusCode(Status.OK.getStatusCode())
    			.extract()
    			.asString()
    			;
    	Approvals.verifyJson(resultado);
    }
    
    /**
     * 
     */
    @Test
    @DataSet(value = "pratos-cenario-1.yml")
    public void testAtualizarPrato() {
    	AtualizarPratoDto atualizarPratoDto = new AtualizarPratoDto();
    	atualizarPratoDto.nome = "Novo nome";
    	given()
    	.with()
    	.pathParam("idRestaurante", ID_RESTAURANTE)
    	.pathParam("id", ID_PRATO)
    	.body(atualizarPratoDto)
    	.when()
    	.put("/restaurantes/{idRestaurante}/pratos/{id}")
    	.then()
    	.statusCode(Status.NO_CONTENT.getStatusCode())
    	;
    	
    	Prato prato = Prato.findById(ID_PRATO);
    	Assert.assertEquals(atualizarPratoDto.nome, prato.nome);
    }
    
    /**
     * 
     */
    @Test
    @DataSet(value = "pratos-cenario-1.yml", cleanAfter = true)    
    public void testDeletarPrato() {
    	given()
    	.with()
    	.pathParam("idRestaurante", ID_RESTAURANTE)
    	.pathParam("id", ID_PRATO)
    	.when()
    	.delete("/restaurantes/{idRestaurante}/pratos/{id}")
    	.then()
    	.statusCode(Status.NO_CONTENT.getStatusCode())
    	;
    	
    	Optional<Prato> prato = Prato.findByIdOptional(ID_PRATO);
    	Assert.assertTrue(prato.isEmpty());
    }
    
    /**
     * 
     */
    @Test
    public void testAdicionarPrato() {
    	Prato pratoInsercao = new Prato();
    	pratoInsercao.nome = "Novo prato";
    	given()
    	.pathParam("idRestaurante", ID_RESTAURANTE)
    	.body(pratoInsercao)
    	.when()
    	.post("/restaurantes/{idRestaurante}/pratos")
    	.then()
    	.statusCode(Status.CREATED.getStatusCode())
    	;
    }

}