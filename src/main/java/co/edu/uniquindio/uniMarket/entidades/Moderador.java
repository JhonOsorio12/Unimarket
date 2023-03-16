package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Moderador extends Persona implements Serializable {
    @Id
    //autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @OneToMany(mappedBy = "moderadorPM")
    private List<Producto_Moderador> moderadorProducto;

    @OneToMany(mappedBy = "moderador")
    private List<CentroAyuda> centroAyuda;
}
