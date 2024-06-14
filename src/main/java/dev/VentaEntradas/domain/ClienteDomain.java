package dev.VentaEntradas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDomain {
	private Integer id;
	private String documento;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccion;
}
