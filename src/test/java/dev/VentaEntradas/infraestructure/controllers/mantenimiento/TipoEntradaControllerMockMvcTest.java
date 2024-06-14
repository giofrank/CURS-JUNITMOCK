package dev.VentaEntradas.infraestructure.controllers.mantenimiento;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import dev.VentaEntradas.application.service.mantenimiento.TipoEntradaService;
import dev.VentaEntradas.domain.TipoEntradaDomain;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@Slf4j
@SpringBootTest(classes = { TipoEntradaController.class })
@EnableWebMvc
public class TipoEntradaControllerMockMvcTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TipoEntradaService tipoEntradaService;
	
	
	@Test
	public void testIsNotNull() {
		assertThat(tipoEntradaService).isNotNull();
	}
	
	@Test
	public void others() throws Exception{
		List<TipoEntradaDomain> retlist = tipoEntradaService.findByObjects(null);
		tipoEntradaService.findByObjects(null).forEach(System.out::println);
		log.info("retlist, {}", retlist);
		
		mockMvc.perform(get("/venta-entradas/tipo-entrada/115"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		//ResultActions result = mockMvc.perform(get("/venta-entradas/tipo-entrada")).andDo(print());
		
		// Then
		//result.andExpect(status().isOk());
		//result.andExpect(jsonPath("$[0].nombre",equalTo("LAPTOP  ASUS VIVOBOOK PRO")));

		// Info
		//log.info("result ->" + result.andReturn().getResponse().getContentAsString());
				
		//assertThat(result).isNotNull();
	}
}
