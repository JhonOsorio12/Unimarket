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

    //para dejar el atributo not null y con un tamaño de caracteres
    @Column(nullable = false, length = 100)
    private String direccion;

    //para dejar el atributo not null y con un tamaño de caracteres
    @Column(nullable = false, length = 12)
    private String telefono;

    @ManyToMany
    @JoinTable(name = "favorito")
    @ToString.Exclude
    private List<Producto> favoritos;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Producto> productos;

    @OneToMany(mappedBy = "usuarioCOM")
    @ToString.Exclude
    private List<Comentario> usuarioComentario;

    @OneToOne(mappedBy = "usuario")
    private CuentaPremium cuentaPremium;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<CentroAyuda> centroAyuda;


}
