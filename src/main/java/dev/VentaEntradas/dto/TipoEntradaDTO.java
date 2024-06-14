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
public class TipoEntradaDTO {
	private Integer id;
	@NotNull(message = "El precioBase es obligatorio")
	@Size(min = 3)
	private String nombre;
	@NotNull(message = "El precioBase es obligatorio")
	private Double precioBase;
}
