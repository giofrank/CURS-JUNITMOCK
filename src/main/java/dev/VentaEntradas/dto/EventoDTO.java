package dev.VentaEntradas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class EventoDTO {
	private Integer id;
	@NotNull(message = "El nombre es obligatorio")
	private String nombre;
	@NotNull(message = "El descripcion es obligatorio")
	private String descripcion;
	@NotNull
	private LocalDate fecha;
	@NotNull
	private LocalTime hora;
	@Positive(message = "El lugar es obligatorio")
	private Integer lugarId; 
}
