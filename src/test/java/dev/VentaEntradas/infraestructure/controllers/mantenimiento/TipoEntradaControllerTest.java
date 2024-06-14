package dev.VentaEntradas.infraestructure.controllers.mantenimiento;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.VentaEntradas.dto.TipoEntradaDTO;
import dev.VentaEntradas.infraestructure.entities.TipoEntradaEntity;
import dev.VentaEntradas.infraestructure.utils.ServiceException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.equalTo;


@TestMethodOrder(value =OrderAnnotation.class )
public class TipoEntradaControllerTest {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		  baseURI = "http://localhost:8085/venta-entradas/";
	}

	@Order(1)
	@DisplayName("01.- Verificar la búsqueda de objeto por id no existente")
	@Test
	public void testFindById_NoContent() {
	        given()
	            .when()
	                .get("tipo-entrada/115")
	            .then()
	                .statusCode(204);
	 }
	@Order(2)
	@DisplayName("02.- Verificar registro de objeto")
	@Test
	void testSave() throws ServiceException, JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		String json = objectMapper.writeValueAsString(
		TipoEntradaDTO.builder().nombre("prubasJsonv3").precioBase(20D).build());
		
		
		given()
		.contentType("application/json")
		.body(json)
		.when()
		.post("tipo-entrada")
		.then()
		.statusCode(201)
		.body("id", notNullValue())
		.body("nombre", equalTo("prubasJsonv3"))
		;
	}
	
	@Order(3)
	@DisplayName("01.- Verificar la búsqueda de objeto por id existente")
	@Test
	public void testFindById_OK(){
	        given()
	            .when()
	                .get("tipo-entrada/1")
	            .then()
	                .statusCode(200);
	 }
}
