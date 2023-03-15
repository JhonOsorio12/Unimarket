package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario  extends Persona implements Serializable {
    @Id
    //autoinclementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //para dejar el atributo not null y con un tamaño de caracteres
    @Column(nullable = false, length = 100)
    private String direccion;

    //para dejar el atributo not null y con un tamaño de caracteres
    @Column(nullable = false, length = 10)
    private Integer telefono;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;


}
