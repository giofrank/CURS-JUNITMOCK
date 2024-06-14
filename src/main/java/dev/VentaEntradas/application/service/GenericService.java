package dev.VentaEntradas.application.service;

import java.util.List;
import java.util.Optional;

import dev.VentaEntradas.infraestructure.utils.ServiceException;

public interface GenericService<T,D> {
	
	List<D> findByObjects( T t) throws ServiceException;
	
	Optional<D> findById(Integer id)throws ServiceException;
	
	T save(T t) throws ServiceException;
	
	T update(T t) throws ServiceException;
	
	void delete( Integer id)throws ServiceException;
}
