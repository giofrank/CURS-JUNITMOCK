package dev.VentaEntradas.application.service.mantenimiento.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.VentaEntradas.application.service.mantenimiento.AsientoService;
import dev.VentaEntradas.domain.AsientoDomain;
import dev.VentaEntradas.dto.AsientoDTO;
import dev.VentaEntradas.infraestructure.entities.AsientoEntity;
import dev.VentaEntradas.infraestructure.entities.LugarEntity;
import dev.VentaEntradas.infraestructure.jpa.AsientoJpa;
import dev.VentaEntradas.infraestructure.jpa.LugarJpa;
import dev.VentaEntradas.infraestructure.utils.MapperUtil;
import dev.VentaEntradas.infraestructure.utils.ServiceException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsientoServiceImpl implements AsientoService {
	@Autowired
	private  AsientoJpa asientoJpa;
	@Autowired
	private  LugarJpa lugarJpa;
	@Autowired
	private  MapperUtil mapperUtil;
	
	@Override
	public List<AsientoDomain> findByObjects(AsientoDTO t) throws ServiceException {
		List<AsientoEntity> lista = asientoJpa.findByEstado("1");
		return mapperUtil.mapperList(lista, AsientoDomain.class);
	}

	@Override
	public Optional<AsientoDomain> findById(Integer id) throws ServiceException {
		Optional<AsientoEntity> entity = asientoJpa.findById(id);
		if (entity.isPresent()) {
			return Optional.ofNullable(mapperUtil.mapper(entity.get(),AsientoDomain.class ));
		}
		return Optional.empty();
	}

	@Override
	public AsientoDTO save(AsientoDTO t) throws ServiceException {
		
		AsientoEntity entity = mapperUtil.mapper(t, AsientoEntity.class);

		LugarEntity lugar = lugarJpa.findById(t.getLugarId()).orElseThrow(()-> new ServiceException("IDENTITY NOT FOUNT: "+ t.getLugarId()));
		
		entity.setLugar(lugar);
		asientoJpa.save(entity);
		t.setId(entity.getId());
		return t;
	}

	@Override
	public AsientoDTO update(AsientoDTO t) throws ServiceException {
		asientoJpa.findById(t.getId()).orElseThrow(()-> new ServiceException("IDENTITY PRIMARY NOT FOUNT: "+ t.getId()));
		AsientoEntity entity = mapperUtil.mapper(t, AsientoEntity.class);
		LugarEntity lugar = lugarJpa.findById(t.getLugarId()).orElseThrow(()-> new ServiceException("IDENTITY FOREING NOT FOUNT: "+ t.getLugarId()));
		entity.setLugar(lugar);
		asientoJpa.save(entity);
		return t;
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		AsientoEntity entity = asientoJpa.findById(id).orElseThrow(()-> new ServiceException("IDENTITY PRIMARY NOT FOUNT: "+ id));
		entity.setEstado("0");
		asientoJpa.save(entity);
		
		//TODO ENTRADA
	}

}
