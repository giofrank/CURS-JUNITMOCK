package dev.VentaEntradas.application.service.ventaentrada.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.VentaEntradas.application.service.ventaentrada.VentaService;
import dev.VentaEntradas.domain.AsientoDomain;
import dev.VentaEntradas.domain.EntradaDetalleDomain;
import dev.VentaEntradas.dto.EntradaPagoDTO;
import dev.VentaEntradas.infraestructure.entities.AsientoEntity;
import dev.VentaEntradas.infraestructure.entities.ClienteEntity;
import dev.VentaEntradas.infraestructure.entities.EntradaEntity;
import dev.VentaEntradas.infraestructure.entities.EventoEntity;
import dev.VentaEntradas.infraestructure.entities.PagoEntity;
import dev.VentaEntradas.infraestructure.entities.TipoEntradaEntity;
import dev.VentaEntradas.infraestructure.jpa.AsientoJpa;
import dev.VentaEntradas.infraestructure.jpa.ClienteJpa;
import dev.VentaEntradas.infraestructure.jpa.EntradaJpa;
import dev.VentaEntradas.infraestructure.jpa.EventoJpa;
import dev.VentaEntradas.infraestructure.jpa.PagoJpa;
import dev.VentaEntradas.infraestructure.jpa.TipoEntradaJpa;
import dev.VentaEntradas.infraestructure.utils.MapperUtil;
import dev.VentaEntradas.infraestructure.utils.ServiceException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {
	
	private final AsientoJpa asientoJpa;
	private final EventoJpa eventoJpa;
	private final ClienteJpa clienteJpa;
	private final TipoEntradaJpa tipoEntradaJpa;
	private final EntradaJpa entradaJpa;
	private final PagoJpa pagoJpa;
	private final MapperUtil mapperUtil;
	
	@Override
	public List<AsientoDomain> buscaAsientosDisponiblesPorEventoId(Integer eventoId) throws ServiceException {
		List<AsientoEntity> objects = asientoJpa.buscaAsientosDisponiblesPorEventoId(eventoId);
		
		return mapperUtil.mapperList(objects, AsientoDomain.class);
	}

	@Transactional
	public Integer procesarEntradaPago(EntradaPagoDTO dto) throws ServiceException {
		try {
			
		
		EventoEntity evento = eventoJpa.findById(dto.getEventoId())
		.orElseThrow(()-> new ServiceException("IDENTITY EVENTO NOT FOUNT: "+ dto.getEventoId()));
		
		ClienteEntity cliente = clienteJpa.findById(dto.getClienteId())
				.orElseThrow(()-> new ServiceException("IDENTITY CLIENTE NOT FOUNT: "+ dto.getClienteId()));
		
		TipoEntradaEntity tipoEntrada = tipoEntradaJpa.findById(dto.getTipoEntradaId())
				.orElseThrow(()-> new ServiceException("IDENTITY TIPO ENTRADA NOT FOUNT: "+ dto.getTipoEntradaId()));
		
		asientoJpa.buscaAsientosDisponiblesPorEventoIdyId(dto.getEventoId(), dto.getAsientoId())
				.orElseThrow(()-> new ServiceException("IDENTITY ASIENTO OCUPADO:  "+ dto.getAsientoId()));
		
		AsientoEntity asiento = asientoJpa.findById(dto.getAsientoId())
				.orElseThrow(()-> new ServiceException("IDENTITY ASIENTO NOT FOUNT: "+ dto.getAsientoId()));
				
		Double precioFinal = tipoEntrada.getPrecioBase();
		
		EntradaEntity entrada = EntradaEntity.builder()
										.evento(evento)
										.cliente(cliente)
										.tipoEntrada(tipoEntrada)
										.asiento(asiento)
										.precioFinal(precioFinal)
										.build(); 
		entradaJpa.save(entrada);
		
		PagoEntity pago  = PagoEntity.builder()
									.entrada(entrada)
									.metodoPago(dto.getMetodoPago())
									.monto(dto.getMontoPagado())
									.build();
		pagoJpa.save(pago);
		
		return entrada.getId();
		
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Optional<EntradaDetalleDomain> detalleVentaEntradaCuuid(String entradaCuuid) {
		Optional<EntradaEntity> entrada =  entradaJpa.findByCuuidAndEstado(entradaCuuid, "1");
		if (entrada.isPresent()) {
			EntradaDetalleDomain entradaDetalle = mapperUtil.mapper(entrada.get(), EntradaDetalleDomain.class);
			Optional<PagoEntity> pago = pagoJpa.findByEntradaId(entrada.get().getId());
			if (pago.isEmpty()) return Optional.empty();
			entradaDetalle.setMontoPagado(pago.get().getMonto());
			entradaDetalle.setMetodoPago(pago.get().getMetodoPago());
			
			return Optional.ofNullable(entradaDetalle);
		}
		
		return Optional.empty();
	}

	

}
