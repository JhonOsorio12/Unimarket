package co.edu.uniquindio.uniMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductoModeradorDTO {

    private String motivo;

    private int codigoProducto;

    private int codigoModerador;
}
