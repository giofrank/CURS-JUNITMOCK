package dev.VentaEntradas.infraestructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.VentaEntradas.infraestructure.entities.EventoEntity;

public interface EventoJpa extends JpaRepository<EventoEntity, Integer> {

	List<EventoEntity> findByEstado(String string);

}
