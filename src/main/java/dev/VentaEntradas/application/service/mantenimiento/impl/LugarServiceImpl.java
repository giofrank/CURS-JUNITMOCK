package dev.VentaEntradas.application.service.mantenimiento.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.VentaEntradas.application.service.mantenimiento.LugarService;
import dev.VentaEntradas.domain.LugarDomain;
import dev.VentaEntradas.dto.LugarDTO;
import dev.VentaEntradas.infraestructure.entities.LugarEntity;
import dev.VentaEntradas.infraestructure.jpa.LugarJpa;
import dev.VentaEntradas.infraestructure.utils.MapperUtil;
import dev.VentaEntradas.infraestructure.utils.ServiceException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LugarServiceImpl implements LugarService {
	private final LugarJpa lugarJpa;
	private final MapperUtil mapperUtil;

	@Override
	public List<LugarDomain> findByObjects(LugarDTO t) throws ServiceException {
		List<LugarEntity> lista= lugarJpa.findByEstado("1");
		return mapperUtil.mapperList(lista, LugarDomain.class);
	}

	@Override
	public Optional<LugarDomain> findById(Integer id) throws ServiceException {
		Optional<LugarEntity> entity = lugarJpa.findById(id);
		if (entity.isPresent()) {
			return Optional.ofNullable(mapperUtil.mapper(entity.get(),LugarDomain.class ));
		}
		return Optional.empty();
	}

	@Override
	public LugarDTO save(LugarDTO t) throws ServiceException {
		LugarEntity entity = mapperUtil.mapper(t, LugarEntity.class);
		
		return mapperUtil.mapper(lugarJpa.save(entity),LugarDTO.class);
	}

	@Override
	public LugarDTO update(LugarDTO t) throws ServiceException {
		Integer id =  t.getId();
		lugarJpa.findById(id).orElseThrow(()-> new ServiceException("IDENTITY NOT FOUNT: "+ id));
		LugarEntity entity =  mapperUtil.mapper(t, LugarEntity.class);
		
		return mapperUtil.mapper(lugarJpa.save(entity), LugarDTO.class);
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		LugarEntity entity = lugarJpa.findById(id).orElseThrow(()-> new ServiceException("IDENTITY NOT FOUNT: "+ id));
		entity.setEstado("0");
		lugarJpa.save(entity);
		//TODO EVENTOS
		
		//TODO ASIENTOS
	}

}
