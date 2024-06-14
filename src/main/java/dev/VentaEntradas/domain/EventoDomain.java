package dev.VentaEntradas.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDomain {
	private Integer id;
	private String nombre;
	private String descripcion;
	private LocalDate fecha;
	private LocalTime hora;
	private LugarDomain lugar; 
}
