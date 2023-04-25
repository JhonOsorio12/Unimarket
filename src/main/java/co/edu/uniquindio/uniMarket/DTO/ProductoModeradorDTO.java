package co.edu.uniquindio.uniMarket.DTO;

import co.edu.uniquindio.uniMarket.entidades.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductoModeradorDTO {

    private String motivo;

    private Estado estado;

    private int codigoProducto;

    private int codigoModerador;
}
