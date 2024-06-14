package dev.VentaEntradas.infraestructure.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.VentaEntradas.infraestructure.entities.ClienteEntity;

public interface ClienteJpa extends JpaRepository<ClienteEntity, Integer> {

	List<ClienteEntity> findByEstado(String string);


	Optional<ClienteEntity> findByDocumento(String clienteDocumento);

}
