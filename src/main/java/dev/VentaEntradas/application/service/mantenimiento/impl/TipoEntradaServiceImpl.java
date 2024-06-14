package dev.VentaEntradas.application.service.mantenimiento.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.VentaEntradas.application.service.mantenimiento.TipoEntradaService;
import dev.VentaEntradas.domain.TipoEntradaDomain;
import dev.VentaEntradas.dto.TipoEntradaDTO;
import dev.VentaEntradas.infraestructure.entities.TipoEntradaEntity;
import dev.VentaEntradas.infraestructure.jpa.TipoEntradaJpa;
import dev.VentaEntradas.infraestructure.utils.MapperUtil;
import dev.VentaEntradas.infraestructure.utils.ServiceException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoEntradaServiceImpl implements TipoEntradaService {
	
	private final TipoEntradaJpa tipoEntradaJpa;
	private final MapperUtil mapperUtil;

	@Override
	public List<TipoEntradaDomain> findByObjects(TipoEntradaDTO t) throws ServiceException {
		List<TipoEntradaEntity> lista= tipoEntradaJpa.findByEstado("1");
		return mapperUtil.mapperList(lista, TipoEntradaDomain.class);
	}

	@Override
	public Optional<TipoEntradaDomain> findById(Integer id) throws ServiceException {
		Optional<TipoEntradaEntity> entity = tipoEntradaJpa.findById(id);
		if (entity.isPresent()) {
			return Optional.ofNullable(mapperUtil.mapper(entity.get(),TipoEntradaDomain.class ));
		}
		return Optional.empty();
	}

	@Override
	public TipoEntradaDTO save(TipoEntradaDTO t) throws ServiceException {
		TipoEntradaEntity entity = mapperUtil.mapper(t, TipoEntradaEntity.class);
		
		return mapperUtil.mapper(tipoEntradaJpa.save(entity),TipoEntradaDTO.class);
	}

	@Override
	public TipoEntradaDTO update(TipoEntradaDTO t) throws ServiceException {
		Integer id =  t.getId();
		tipoEntradaJpa.findById(id).orElseThrow(()-> new ServiceException("IDENTITY NOT FOUNT: "+ id));
		TipoEntradaEntity entity =  mapperUtil.mapper(t, TipoEntradaEntity.class);
		
		return mapperUtil.mapper(tipoEntradaJpa.save(entity), TipoEntradaDTO.class);
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		TipoEntradaEntity entity = tipoEntradaJpa.findById(id).orElseThrow(()-> new ServiceException("IDENTITY NOT FOUNT: "+ id));
		entity.setEstado("0");
		tipoEntradaJpa.save(entity);
		//TODO ENTRADA
	}
}
