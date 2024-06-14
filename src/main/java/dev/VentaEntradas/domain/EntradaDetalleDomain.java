package dev.VentaEntradas.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntradaDetalleDomain {
	private String cuuid;
	private EventoDomain evento;
	private ClienteDomain cliente;
	private TipoEntradaDomain tipoEntrada;
	private AsientoDomain asiento;
	private LocalDateTime fechaCompra;
	private Double montoPagado;
	private String metodoPago;
	

}
