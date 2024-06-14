package dev.VentaEntradas.infraestructure.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "tipo_documento", nullable = false)
	private String tipoDocumento= "01";
	
	@Column(nullable = false)
	private String documento;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String telefono;
	
	private String direccion;
	
	@Column(length = 1, nullable = false)
    private String estado = "1";
	
	
}
