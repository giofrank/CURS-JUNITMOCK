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
public class EntradaPagoDTO {
	@Positive(message = "Debe ser un evento valido")
	private Integer eventoId;
	@Positive(message = "Debe ser un cliente valido")
	private Integer clienteId;
	@Positive(message = "Debe ser un tipo de entrada valido")
	private Integer tipoEntradaId;
	@Positive(message = "Debe ser un asiento valido")
	private Integer asientoId;
	@Positive(message = "Debe ser un monto valido")
	private Double montoPagado;
	@NotNull(message = "Debe ser un metodo de Pago valido")
	private String  metodoPago;
}