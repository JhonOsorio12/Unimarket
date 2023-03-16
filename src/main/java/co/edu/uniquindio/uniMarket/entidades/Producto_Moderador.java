package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto_Moderador implements Serializable {
    @Id
    //autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //para dejar el atributo not null y con un tamaño de caracteres
    @Column(nullable = false , length = 1000)
    private String motivo;

    //para dejar el atributo not null
    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @ManyToOne
    private Moderador moderadorPM;

    @ManyToOne
    private Producto productoPM;


}