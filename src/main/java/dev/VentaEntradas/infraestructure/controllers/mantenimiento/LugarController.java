package dev.VentaEntradas.infraestructure.controllers.mantenimiento;

import java.util.List;
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
import static java.util.Objects.isNull;
import dev.VentaEntradas.application.service.mantenimiento.LugarService;
import dev.VentaEntradas.domain.LugarDomain;
import dev.VentaEntradas.dto.LugarDTO;
import dev.VentaEntradas.infraestructure.utils.ServiceException;

@RestController
@RequestMapping("/lugar")
public class LugarController {
	@Autowired
	private LugarService lugarService;
	
	@GetMapping("")
	public ResponseEntity<?> findAll() throws ServiceException {
		List<LugarDomain> lista = lugarService.findByObjects(null);
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<?> findById(@PathVariable Integer id) throws ServiceException {
		Optional<LugarDomain> objecto = lugarService.findById(id);
		if (objecto.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(objecto.get());
	}
	
	@PostMapping 
	public ResponseEntity<?> save( @RequestBody @Valid LugarDTO dto, BindingResult bindingResult ) throws ServiceException {
		if (bindingResult.hasErrors()) {
	       return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
	    }
		LugarDTO objeto = lugarService.save(dto);
		if (isNull(objeto)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(objeto); // 201
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid LugarDTO dto,  BindingResult bindingResult)
			throws ServiceException {
		if (bindingResult.hasErrors()) {			
		    return ResponseEntity.badRequest().body("Errores de validación: " + bindingResult.getAllErrors());
		}
		dto.setId(id);
		LugarDTO objeto = lugarService.update(dto);
		if (isNull(objeto)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(objeto); // 200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLogic(@PathVariable Integer id) throws ServiceException {
		lugarService.delete(id);
		return ResponseEntity.ok().build();
	}
}
