package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Moderador extends Persona implements Serializable {

    @OneToMany(mappedBy = "moderadorPM")
    private List<ProductoModerador> moderadorProducto;

    @OneToMany(mappedBy = "moderador")
    private List<CentroAyuda> centroAyuda;
}
