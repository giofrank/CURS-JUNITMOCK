package dev.VentaEntradas.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LugarDTO {
	private Integer id;
	@NotNull(message = "El nombre es obligatorio")
	@Size(min=10 , max = 255)
	private String nombre;
	@NotNull(message = "El direccion es obligatorio")
	@Size(min=10 , max = 255)
	private String direccion;
	@NotNull(message = "El capacidad es obligatorio")
	private Integer capacidad;
}
