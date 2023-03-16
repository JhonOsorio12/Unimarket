package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Detalle_Compra implements Serializable {
    @Id
    //autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //para dejar el atributo no null
    @Column(nullable = false)
    private float precio_producto;

    @Positive
    //para dejar el atributo not null
    @Column(nullable = false)
    private Integer unidades;

    @ManyToOne
    private Producto productoDT;
    @ManyToOne
    private Compra compraDT;



}
