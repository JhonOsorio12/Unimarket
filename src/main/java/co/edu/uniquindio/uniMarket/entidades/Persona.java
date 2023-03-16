package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //para dejar el atributo not null
    @Column(nullable = false)
    private String nombre;

    //para dejar el atributo not null, con un tamaño de caracteres y que sea unico
    @Column(nullable = false , length = 100, unique = true)
    private String email;

    //para dejar el atributo not null
    @Column(nullable = false)
    private String password;
}
