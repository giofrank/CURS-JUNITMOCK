package dev.VentaEntradas.application.service.mantenimiento.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.VentaEntradas.application.service.mantenimiento.ClienteService;
import dev.VentaEntradas.domain.ClienteDomain;
import dev.VentaEntradas.dto.ClienteDTO;
import dev.VentaEntradas.infraestructure.entities.ClienteEntity;
import dev.VentaEntradas.infraestructure.jpa.ClienteJpa;
import dev.VentaEntradas.infraestructure.utils.MapperUtil;
import dev.VentaEntradas.infraestructure.utils.ServiceException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
	private final ClienteJpa clienteJpa;
	private final MapperUtil mapperUtil;
	
	@Override
	public List<ClienteDomain> findByObjects(ClienteDTO t) throws ServiceException {
		List<ClienteEntity> lista = clienteJpa.findByEstado("1");
		return mapperUtil.mapperList(lista, ClienteDomain.class);
	}

	@Override
	public Optional<ClienteDomain> findById(Integer id) throws ServiceException {
		Optional<ClienteEntity> entity = clienteJpa.findById(id);
		if (entity.isPresent()) {
			return Optional.ofNullable(mapperUtil.mapper(entity.get(),ClienteDomain.class ));
		}
		return Optional.empty();
	}

	@Override
	public ClienteDTO save(ClienteDTO t) throws ServiceException {
		Optional<ClienteEntity> entity = clienteJpa.findByDocumento(t.getDocumento());
		if (entity.isPresent()) {
			//throw new ServiceException("YA SE ENCUENTRA REGISTRADO"); 
			return mapperUtil.mapper(entity.get(),ClienteDTO.class );
		}
		
		ClienteEntity entityNew = mapperUtil.mapper(t, ClienteEntity.class);
		clienteJpa.save(entityNew);
		t.setId(entityNew.getId());
		return t;
	}

	@Override
	public ClienteDTO update(ClienteDTO t) throws ServiceException {
		clienteJpa.findById(t.getId()).orElseThrow(()-> new ServiceException("IDENTITY PRIMARY NOT FOUNT: "+ t.getId()));
		ClienteEntity entity = mapperUtil.mapper(t, ClienteEntity.class);
		clienteJpa.save(entity);
		return t;
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		ClienteEntity entity = clienteJpa.findById(id).orElseThrow(()-> new ServiceException("IDENTITY PRIMARY NOT FOUNT: "+ id));
		entity.setEstado("0");
		clienteJpa.save(entity);
		
		//TODO ENTRADA
	}

	@Override
	public Optional<ClienteDomain> buscaClientePorDocumento(String clienteDocumento) {
		Optional<ClienteEntity> entity = clienteJpa.findByDocumento(clienteDocumento);
		if (entity.isPresent()) {
			return Optional.ofNullable(mapperUtil.mapper(entity.get(),ClienteDomain.class ));
		}
		return Optional.empty();
	}

}
