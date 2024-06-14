package dev.VentaEntradas.infraestructure.controllers.ventaentrada;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.VentaEntradas.application.service.mantenimiento.ClienteService;
import dev.VentaEntradas.application.service.ventaentrada.VentaService;
import dev.VentaEntradas.domain.AsientoDomain;
import dev.VentaEntradas.domain.ClienteDomain;
import dev.VentaEntradas.domain.EntradaDetalleDomain;
import dev.VentaEntradas.dto.ClienteDTO;
import dev.VentaEntradas.dto.EntradaPagoDTO;
import dev.VentaEntradas.infraestructure.utils.ServiceException;

@RestController
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	private VentaService ventaService;
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("asientos-disponibles/{eventoId}") 
	public ResponseEntity<?> buscaAsientosDisponiblesPorEventoId(@PathVariable Integer eventoId) throws ServiceException {
		List<AsientoDomain> lista = ventaService.buscaAsientosDisponiblesPorEventoId(eventoId);
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("buscar-cliente/{clienteDocumento}") 
	public ResponseEntity<?> buscaClientePorDocumento(@PathVariable String clienteDocumento) throws ServiceException {
		Optional<ClienteDomain> objecto = clienteService.buscaClientePorDocumento(clienteDocumento);
		if (objecto.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(objecto.get());
	}
	
	@PostMapping("registrar-cliente") 
	public ResponseEntity<?> registrarCliente( @RequestBody @Valid ClienteDTO dto, BindingResult bindingResult ) throws ServiceException {
		if (bindingResult.hasErrors()) {
	       return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
	    }
		ClienteDTO objeto = clienteService.save(dto);
		if (Objects.isNull(objeto)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(objeto); // 201
	}
	
	
	@PostMapping("procesar-entrada-pago") 
	public ResponseEntity<?> procesarEntradaPago( @RequestBody @Valid EntradaPagoDTO dto, BindingResult bindingResult ) throws ServiceException {
		if (bindingResult.hasErrors()) {
	       return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
	    }
		Integer entrada = ventaService.procesarEntradaPago(dto);
	
		return ResponseEntity.status(HttpStatus.CREATED).body("Entrada generada:"+entrada); // 201
	}

	@GetMapping("detalle-compra/{entradaCuuid}") 
	public ResponseEntity<?> detalleVentaEntradaCuuid(@PathVariable String entradaCuuid) throws ServiceException {
		Optional<EntradaDetalleDomain> objecto = ventaService.detalleVentaEntradaCuuid(entradaCuuid);
		if (objecto.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(objecto.get());
	}


}
