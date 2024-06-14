package dev.VentaEntradas.infraestructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.VentaEntradas.infraestructure.entities.TipoEntradaEntity;

public interface TipoEntradaJpa extends JpaRepository<TipoEntradaEntity, Integer> {

	List<TipoEntradaEntity> findByEstado(String string);

}
