package dev.VentaEntradas.application.service.ventaentrada;

import java.util.List;
import java.util.Optional;

import dev.VentaEntradas.domain.AsientoDomain;
import dev.VentaEntradas.domain.EntradaDetalleDomain;
import dev.VentaEntradas.dto.EntradaPagoDTO;
import dev.VentaEntradas.infraestructure.utils.ServiceException;

public interface VentaService {

	List<AsientoDomain> buscaAsientosDisponiblesPorEventoId(Integer eventoId)throws ServiceException;

	Integer procesarEntradaPago(EntradaPagoDTO dto)throws ServiceException;

	Optional<EntradaDetalleDomain> detalleVentaEntradaCuuid(String entradaCuuid);

	
}
