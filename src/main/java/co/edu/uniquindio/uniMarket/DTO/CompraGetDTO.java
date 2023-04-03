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
public class CompraGetDTO {

    private Integer codigoCompra;

    private LocalDateTime fechaCreacion;

    private float valorTotal;

    private MetodoPago metodoPago;

    private Integer codigoUsuario;

    private List<DetalleCompraDTO> detalleCompraDTO;


}
