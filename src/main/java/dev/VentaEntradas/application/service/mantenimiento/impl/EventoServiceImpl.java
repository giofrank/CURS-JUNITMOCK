package dev.VentaEntradas.application.service.mantenimiento.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.VentaEntradas.application.service.mantenimiento.EventoService;
import dev.VentaEntradas.domain.EventoDomain;
import dev.VentaEntradas.dto.EventoDTO;
import dev.VentaEntradas.infraestructure.entities.EventoEntity;
import dev.VentaEntradas.infraestructure.entities.LugarEntity;
import dev.VentaEntradas.infraestructure.jpa.EventoJpa;
import dev.VentaEntradas.infraestructure.jpa.LugarJpa;
import dev.VentaEntradas.infraestructure.utils.MapperUtil;
import dev.VentaEntradas.infraestructure.utils.ServiceException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService{
	private final EventoJpa eventoJpa;
	private final MapperUtil mapperUtil;
	private final LugarJpa lugarJpa;

	@Override
	public List<EventoDomain> findByObjects(EventoDTO t) throws ServiceException {
		List<EventoEntity> lista = eventoJpa.findByEstado("1");
		return mapperUtil.mapperList(lista, EventoDomain.class);
	}

	@Override
	public Optional<EventoDomain> findById(Integer id) throws ServiceException {
		Optional<EventoEntity> entity = eventoJpa.findById(id);
		if (entity.isPresent()) {
			return Optional.ofNullable(mapperUtil.mapper(entity.get(),EventoDomain.class ));
		}
		return Optional.empty();
	}

	@Override
	public EventoDTO save(EventoDTO t) throws ServiceException {
		EventoEntity entity = mapperUtil.mapper(t, EventoEntity.class);

		LugarEntity lugar = lugarJpa.findById(t.getLugarId()).orElseThrow(()-> new ServiceException("IDENTITY NOT FOUNT: "+ t.getLugarId()));
		
		entity.setLugar(lugar);
		eventoJpa.save(entity);
		t.setId(entity.getId());
		return t;
	}

	@Override
	public EventoDTO update(EventoDTO t) throws ServiceException {
		eventoJpa.findById(t.getId()).orElseThrow(()-> new ServiceException("IDENTITY PRIMARY NOT FOUNT: "+ t.getId()));
		EventoEntity entity = mapperUtil.mapper(t, EventoEntity.class);
		LugarEntity lugar = lugarJpa.findById(t.getLugarId()).orElseThrow(()-> new ServiceException("IDENTITY FOREING NOT FOUNT: "+ t.getLugarId()));
		entity.setLugar(lugar);
		eventoJpa.save(entity);
		return t;
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		EventoEntity entity = eventoJpa.findById(id).orElseThrow(()-> new ServiceException("IDENTITY PRIMARY NOT FOUNT: "+ id));
		entity.setEstado("0");
		eventoJpa.save(entity);
		
		//TODO ENTRADA
		
	}

}
