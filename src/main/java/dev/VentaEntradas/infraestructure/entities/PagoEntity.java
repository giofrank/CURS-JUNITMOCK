package dev.VentaEntradas.infraestructure.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pago")
public class PagoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

    @ManyToOne
    @JoinColumn(name = "entrada_id")
    private EntradaEntity entrada;

    @Column(nullable = false)
    private Double monto;

    @Column(name = "fecha", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;
    
    @Column(length = 1, nullable = false)
    private String estado = "1";

}
