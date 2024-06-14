package dev.VentaEntradas.application.service.mantenimiento;

import java.util.Optional;

import dev.VentaEntradas.application.service.GenericService;
import dev.VentaEntradas.domain.ClienteDomain;
import dev.VentaEntradas.dto.ClienteDTO;

public interface ClienteService  extends GenericService<ClienteDTO, ClienteDomain>{

	Optional<ClienteDomain> buscaClientePorDocumento(String clienteDocumento);

}
