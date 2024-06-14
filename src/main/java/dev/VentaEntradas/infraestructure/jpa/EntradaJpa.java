package dev.VentaEntradas.infraestructure.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.VentaEntradas.infraestructure.entities.EntradaEntity;

public interface EntradaJpa extends JpaRepository<EntradaEntity, Integer> {

	Optional<EntradaEntity> findByCuuidAndEstado(String entradaCuuid, String string);

}
