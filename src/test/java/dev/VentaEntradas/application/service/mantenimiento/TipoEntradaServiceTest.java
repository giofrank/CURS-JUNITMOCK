package dev.VentaEntradas.application.service.mantenimiento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;

import dev.VentaEntradas.domain.TipoEntradaDomain;
import dev.VentaEntradas.dto.TipoEntradaDTO;
import dev.VentaEntradas.infraestructure.utils.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisplayName("Tipo Entrada service Test")
@TestMethodOrder(value = OrderAnnotation.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class TipoEntradaServiceTest {	
	private Integer row_activos;
	
	@Value("${mi.variable.global}")
    private Integer variableGlobal;
	
	
	public TipoEntradaDTO tipoEntradaDTO = TipoEntradaDTO.builder()
											.nombre("Tipo entrada test" + LocalDate.now())
											.precioBase(20D)
											.build();
	
	@Autowired
    private ConfigurableApplicationContext environment;
	
	@Autowired
	private TipoEntradaService entradaService;
	
	@BeforeAll
	static void setUpBeforeClass() throws ServiceException {
		
	}
	@BeforeEach
	//@Disabled
	void setUp() throws ServiceException {
		log.info("setUp..."+variableGlobal);
		
		entradaService.findByObjects(null).forEach(System.out::println);
		
	}
	
	@Order(1)
	@DisplayName("01.- Verificar que Service no sea null")
	//@Disabled
	@Test
	void injectedProductoRepository_AreNotNull() {
		assertThat(entradaService).isNotNull();
	}
	
	@Order(2)
	@Test
	//@Disabled
	@DisplayName("02.- Listar todos los objetos")
	void testFindByObject_All() throws ServiceException {
		List<TipoEntradaDomain> lista= entradaService.findByObjects(null);
		assertEquals(0, lista.size());
	}
	
	@Order(3)
	@Test
	//@Disabled
	@DisplayName("03.- Buscar objeto por código - ERROR")
	void testFindById_ERROR() throws ServiceException {
		assertFalse(entradaService.findById(variableGlobal).isPresent());
	}
	
	@Order(4)
	@Test
	//@Disabled
	@DisplayName("04.- Registrar producto  Domain- OK")
	void testSave_Domain() throws ServiceException {
		TipoEntradaDTO obj= entradaService.save(tipoEntradaDTO);
		System.out.println(obj);
		TestPropertyValues.of("mi.variable.global="+obj.getId()).applyTo(environment);
		assertTrue(obj.getId()>0);
	}
	
	
	@Order(5)
	@Test
	//@Disabled
	@DisplayName("05.- Buscar objeto por código - OK")
	void testFindById_OK() throws ServiceException {
		assertTrue(entradaService.findById(variableGlobal).isPresent());
	}
}
