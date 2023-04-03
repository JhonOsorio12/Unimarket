package co.edu.uniquindio.uniMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DetalleCompraGetDTO {

    private Integer codigo;

    private Integer unidades;

    private float precio;

    private Integer codigoProducto;

    private Integer codigoCompra;


}
