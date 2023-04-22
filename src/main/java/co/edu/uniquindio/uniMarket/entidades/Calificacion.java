package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Calificacion {

    //Este atributo es la PK de la entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //------------------------- Atributos de la entidad -------------------------
    @Positive
    @Column(nullable = false)
    private Integer puntuacion;

    //-------------------------- Entidades Relacionadas ------------------------------------

    //Esta entidad contiene un usuario, que es responsable o el titular de la calificación
    @ManyToOne
    private Usuario usuario;

    //Esta entidad contiene un producto, que es responsable o el titular de recibir la puntuación de la calificación
    @ManyToOne
    private Producto producto;

    //Constructor
    public Calificacion(Integer puntuacion, Usuario usuario, Producto producto) {
        this.puntuacion = puntuacion;
        this.usuario = usuario;
        this.producto = producto;
    }

}
