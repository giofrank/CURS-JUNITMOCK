package dev.VentaEntradas.infraestructure.controllers.mantenimiento;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.VentaEntradas.application.service.mantenimiento.TipoEntradaService;
import dev.VentaEntradas.domain.TipoEntradaDomain;
import dev.VentaEntradas.dto.TipoEntradaDTO;
import dev.VentaEntradas.infraestructure.utils.ServiceException;

@RestController
@RequestMapping("/tipo-entrada")
public class TipoEntradaController {
	@Autowired
	private TipoEntradaService tipoEntradaService;
	
	@GetMapping("")
	public ResponseEntity<?> findAll() throws ServiceException {
		List<TipoEntradaDomain> lista = tipoEntradaService.findByObjects(null);
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<?> findById(@PathVariable Integer id) throws ServiceException {
		Optional<TipoEntradaDomain> objecto = tipoEntradaService.findById(id);
		if (objecto.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(objecto.get());
	}
	
	@PostMapping 
	public ResponseEntity<?> save( @RequestBody @Valid TipoEntradaDTO dto, BindingResult bindingResult ) throws ServiceException {
		if (bindingResult.hasErrors()) {
	       return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
	    }
		TipoEntradaDTO objeto = tipoEntradaService.save(dto);
		if (Objects.isNull(objeto)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(objeto); // 201
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid TipoEntradaDTO dto,  BindingResult bindingResult)
			throws ServiceException {
		if (bindingResult.hasErrors()) {			
		    return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
		}
		dto.setId(id);
		TipoEntradaDTO objeto = tipoEntradaService.update(dto);
		if (Objects.isNull(objeto)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(objeto); // 200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLogic(@PathVariable Integer id) throws ServiceException {
		tipoEntradaService.delete(id);
		return ResponseEntity.ok().build();
	}
}
