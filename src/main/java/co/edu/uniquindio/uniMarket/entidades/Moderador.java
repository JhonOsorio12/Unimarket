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
public class Moderador extends Persona implements Serializable {

    @OneToMany(mappedBy = "moderadorPM")
    @ToString.Exclude
    private List<ProductoModerador> moderadorProducto;

    @OneToMany(mappedBy = "moderador")
    @ToString.Exclude
    private List<CentroAyuda> centroAyuda;
}
