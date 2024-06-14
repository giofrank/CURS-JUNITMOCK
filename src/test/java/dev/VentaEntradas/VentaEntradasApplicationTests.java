package dev.VentaEntradas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VentaEntradasApplicationTests {

	@BeforeAll
	@DisplayName("Init - General")
	static void  initGeneral() {
		System.out.println("Test init-general");
	}
	
	@BeforeEach
	@DisplayName("Init")
	void  init() {
		System.out.println("Test init");
	}
	
	@DisplayName("Configuración inicial")
	@Test
	void contextLoads() {
		assertTrue(true);
		System.out.println("Test configuración inicial");
		msg();
	}


	
	@RepeatedTest(3)
	@DisplayName("Regitro de datos")
	//@Disabled
	@Test
	void metodo2() {
		System.out.println("Test Regitro de datos");
	}
	

	@DisplayName("Eliminación de datos")
	@Test
	void metodo3() {
		System.out.println("Test de eliminación de datos");
		msg();
	}
	
	@AfterEach
	void destroy() {
		System.out.println("Test destroy");
	}
	
	@BeforeAll
	@DisplayName("Destroy - General")
	static void  destroyGeneral() {
		System.out.println("Test destroy-general");
	}
	
	
	void msg() {
		System.out.println("Test proceso");
	}

}
