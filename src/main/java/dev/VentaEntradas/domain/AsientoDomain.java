package dev.VentaEntradas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsientoDomain {
	private Integer id;
	private String fila;
	private Integer numero;
	private LugarDomain lugar;
}
