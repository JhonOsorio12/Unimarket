package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CentroAyuda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigoAyuda;

    @Column(nullable = false, length = 1000)
    private String mensaje;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Moderador moderador;

}
