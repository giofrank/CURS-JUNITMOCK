package dev.VentaEntradas.infraestructure.entities;


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
@Table(name = "asiento")
public class AsientoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String fila;
	
	@Column(nullable = false)
	private Integer numero;
	
	@ManyToOne
	@JoinColumn(name = "lugar_id", nullable =  false)
	private LugarEntity lugar;
	
	@Column(length = 1, nullable = false)
    private String estado ;
	
	@PrePersist 
	public void prePersist() {
	    if (this.estado == null) {
	        this.estado = "1";
	    }
	}

}
