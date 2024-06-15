package dev.VentaEntradas.infraestructure.controllers.mantenimiento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import dev.VentaEntradas.application.service.mantenimiento.TipoEntradaService;
import dev.VentaEntradas.domain.TipoEntradaDomain;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*SE COMENTA NO AUN NECESARIO*/
//@ExtendWith(MockitoExtension.class)
//@EnableWebMvc


/*TODO ERRORES
 * @MockBean => @SpyBean 
 * SE AGREGA AL USAR INTERFACES E IMPLEMENTACIONES
 * @SpringBootTest(classes = { TipoEntradaController.class }) =>
 * @SpringBootTest
 * PARA QUE SCANNEE TODAS LAS CLASES
 * ULTIMO context-path NO ES NECESARIO DECLARAR 
 * */

@AutoConfigureMockMvc
@Slf4j
@SpringBootTest
public class TipoEntradaControllerMockMvcTest {
	@Autowired
	private MockMvc mockMvc;
	
	@SpyBean
	private TipoEntradaService tipoEntradaService;
	
	
	@Test
	public void testIsNotNull() {
		assertThat(tipoEntradaService).isNotNull();
	}
	
	@Test
	public void others() throws Exception{
		List<TipoEntradaDomain> retlist = tipoEntradaService.findByObjects(null);
		log.info("retlist, {}", retlist);
		
		mockMvc.perform(get("/tipo-entrada/2")
		.header("Origin", "http://localhost:4200")) // Simular una solicitud desde otro origen
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		ResultActions result = mockMvc.perform(get("/tipo-entrada")).andDo(print());
		
		// Then
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$[1].nombre",equalTo("prubasJsonv3")));

		// Info
		log.info("result ->" + result.andReturn().getResponse().getContentAsString());
				
		assertThat(result).isNotNull();
	}
}