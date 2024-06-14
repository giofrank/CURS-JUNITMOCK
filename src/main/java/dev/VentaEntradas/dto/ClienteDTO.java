package dev.VentaEntradas.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
	private Integer id;
	@NotNull(message = "El documento es obligatorio")
	private String documento;
	@NotNull(message = "El nombre es obligatorio")
	private String nombre;
	
	@NotNull(message = "El apellido es obligatorio")
	private String apellido;
	
	@NotNull(message = "El email es obligatorio")
	private String email;
	
	@NotNull(message = "El telefono es obligatorio")
	private String telefono;
	
	private String direccion;
}
