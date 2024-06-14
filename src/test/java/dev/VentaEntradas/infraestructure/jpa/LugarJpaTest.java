package dev.VentaEntradas.infraestructure.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import dev.VentaEntradas.infraestructure.entities.LugarEntity;

@DisplayName("Lugar Repository Test")
@TestMethodOrder(value = OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@SqlGroup({
    @Sql(scripts = "/scripts/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(scripts = "/scripts/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class LugarJpaTest {
	@Autowired
	private LugarJpa lugarJpa; 
	
	@BeforeEach
	void setUp() throws Exception {
		lugarJpa.findAll().forEach(System.out::println);
	}
	
	@Order(1)
	@DisplayName("01.- Verificar que Repository sea no null")
	@Test
	void injectedProductoRepository_AreNotNull() {
		assertThat(lugarJpa).isNotNull();

	}
		
	@Order(2)
	@DisplayName("02.- Buscar Estadio por ID")
	@Test
    void testBuscarLugarPorId() {
        LugarEntity lugar = lugarJpa.findById(1).orElse(null);
        assertNotNull(lugar);
        assertEquals("Estadio Monumental", lugar.getNombre());
    }
	
	@Order(3)
	@DisplayName("03.- Verificar la cantidad de elementos insertados")
	@Test
    void testCantidadElementosInsertados() {
        List<LugarEntity> lista = lugarJpa.findByEstado("1");
        assertNotNull(lista);
        assertEquals(3, lista.size());
    }
	
}
