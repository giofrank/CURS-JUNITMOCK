package dev.VentaEntradas.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsientoDTO {
	
	private Integer id;
	@NotNull(message = "El fila es obligatorio")
	private String fila;
	@Positive(message = "El numero es obligatorio")
	private Integer numero;
	@Positive(message = "El lugar es obligatorio")
	private Integer lugarId;
	
}
