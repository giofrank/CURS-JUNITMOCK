package dev.VentaEntradas.infraestructure.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.VentaEntradas.infraestructure.entities.PagoEntity;

public interface PagoJpa extends JpaRepository<PagoEntity, Integer> {

	Optional<PagoEntity> findByEntradaId(Integer id);

}
