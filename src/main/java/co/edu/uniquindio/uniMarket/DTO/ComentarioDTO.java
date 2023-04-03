package co.edu.uniquindio.uniMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ComentarioDTO {

    private String mensaje;

    private Integer codigoProducto;

    private Integer codigoUsuario;
}
