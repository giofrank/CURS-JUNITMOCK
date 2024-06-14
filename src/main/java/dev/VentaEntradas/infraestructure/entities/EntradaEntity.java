package dev.VentaEntradas.infraestructure.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
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
@Table(name = "entrada")
public class EntradaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "evento_id", nullable =  false)
    private EventoEntity evento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "tipo_entrada_id")
    private TipoEntradaEntity tipoEntrada;

    @ManyToOne
    @JoinColumn(name = "asiento_id")
    private AsientoEntity asiento;

    //@Column(name = "fecha_compra", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Column(name = "fecha_compra", insertable = false, updatable = false)
    private LocalDateTime fechaCompra;

    @Column(name = "precio_final", nullable = false)
    private Double precioFinal;
    
    @Column(length = 1, nullable = false)
    private String estado;
    
    @Column(nullable = false, unique = true, columnDefinition = "CHAR(36)")
    private String cuuid;
    
    @PrePersist 
    public void generateCuuid() {
        if (this.cuuid == null) {
        	this.cuuid = UUID.randomUUID().toString();
        }
        if (this.estado == null) {
        	this.estado = "1";
        }
    }

}
