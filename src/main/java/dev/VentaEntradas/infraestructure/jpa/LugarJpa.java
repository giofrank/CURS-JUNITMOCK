package dev.VentaEntradas.infraestructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.VentaEntradas.infraestructure.entities.LugarEntity;

public interface LugarJpa extends JpaRepository<LugarEntity, Integer> {

	List<LugarEntity> findByEstado(String string);

}
