package co.edu.uniquindio.uniMarket.DTO;

import co.edu.uniquindio.uniMarket.entidades.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CompraDTO {

    private Integer codigoUsuario;

    private MetodoPago metodoPago;

    private List<DetalleCompraDTO> detalleCompraDTO;

}
