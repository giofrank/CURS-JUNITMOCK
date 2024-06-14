package dev.VentaEntradas.infraestructure.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.VentaEntradas.infraestructure.entities.AsientoEntity;
import dev.VentaEntradas.infraestructure.entities.EventoEntity;

public interface AsientoJpa extends JpaRepository<AsientoEntity, Integer> {

	List<AsientoEntity> findByEstado(String string);
	
	
	@Query("SELECT a FROM AsientoEntity a "
			+ " JOIN a.lugar l "
			+ " JOIN EventoEntity e ON e.lugar = l.id AND e.estado = '1'"
			+ " WHERE a.estado = '1' "
			+ " AND  e.id = :eventoId "
			+ " AND a.id NOT IN (SELECT en.asiento.id FROM EntradaEntity en WHERE en.evento = e AND en.asiento = a) "
			+ " ORDER BY a.numero")
	List<AsientoEntity> buscaAsientosDisponiblesPorEventoId(@Param("eventoId") Integer eventoId);

	@Query("SELECT a FROM AsientoEntity a "
			+ " JOIN a.lugar l "
			+ " JOIN EventoEntity e ON e.lugar = l.id AND e.estado = '1'"
			+ " WHERE a.estado = '1' "
			+ " AND  e.id = :eventoId AND a.id = :asientoId "
			+ " AND a.id NOT IN (SELECT en.asiento.id FROM EntradaEntity en WHERE en.evento = e AND en.asiento = a) "
			+ " ORDER BY a.numero")
	Optional<EventoEntity> buscaAsientosDisponiblesPorEventoIdyId(@Param("eventoId") Integer eventoId,@Param("asientoId") Integer asientoId);
	
	
}
