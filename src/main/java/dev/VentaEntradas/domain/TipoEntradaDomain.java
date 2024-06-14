package dev.VentaEntradas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEntradaDomain {
	private Integer id;
	private String nombre;
	private Double precioBase;
}
