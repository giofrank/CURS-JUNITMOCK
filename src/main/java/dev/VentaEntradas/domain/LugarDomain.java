package dev.VentaEntradas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LugarDomain {
	private Integer id;
	private String nombre;
	private String direccion;
}
