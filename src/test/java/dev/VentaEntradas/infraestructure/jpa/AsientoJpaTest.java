package dev.VentaEntradas.infraestructure.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;

import dev.VentaEntradas.infraestructure.entities.AsientoEntity;
import dev.VentaEntradas.infraestructure.entities.LugarEntity;

@DisplayName("Asiento Repository Test")
@TestMethodOrder(value = OrderAnnotation.class)
@SpringBootTest
//@TestPropertySource("classpath:test.properties")
public class AsientoJpaTest {
	private Integer row_activos;
	
	@Value("${mi.variable.global}")
    private Integer variableGlobal;
	
	@Autowired
    private ConfigurableApplicationContext environment;

	public AsientoEntity entity = AsientoEntity.builder()
									.fila("E")
									.numero(11)
									.lugar(LugarEntity.builder().id(1).build())
									.build();
	@Autowired
	private AsientoJpa repo;
	
	@BeforeEach
	void setUp() throws Exception {		
		List<AsientoEntity> lista = repo.buscaAsientosDisponiblesPorEventoId(1);	
		row_activos = lista.size();
	
	}
	
	@DisplayName("01.- Verificar que Repository no sea null")
	@Test
	@Order(1)
	void repository_notnull() {
		assertNotNull(repo);	
	}
	
	@DisplayName("02.- Verificar la cantidad de salas")
	@Test
	@Order(2)
	@Disabled
	void all() {
		assertEquals(repo.findAll().size(), repo.findByEstado("1").size());
	}
	
	@DisplayName("03.- Verificar la cantidad de asientos disponibles")
	@Test
	@Order(3)
	void buscaAsientosDisponiblesPorEventoId() {
		System.out.println("cantidad de asientos disponibles =>"+row_activos);
		assertTrue(row_activos >=1);
	}
	
	@DisplayName("04.- Verificar registro")
	@Test
	@Order(4)
	void save() {
		AsientoEntity entidad =  repo.save(entity);
		TestPropertyValues.of("mi.variable.global="+entidad.getId()).applyTo(environment);
		assertNotNull(entidad);
	}
	
	@DisplayName("05.- Verificar actualizacion")
	@Test
	@Order(5)
	void actualizacion() {
		System.out.println(variableGlobal);
		AsientoEntity entidad = repo.findById(variableGlobal).get();
		entidad.setEstado("0");
		repo.save(entidad);
		assertTrue(entidad.getEstado().equals("0"));
	}
}
