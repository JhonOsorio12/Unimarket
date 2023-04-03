package co.edu.uniquindio.uniMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class DetalleCompraDTO {

    private Integer codigoProducto;

    private Integer unidades;

    private float valor;

    private Integer codigoCompra;

}
