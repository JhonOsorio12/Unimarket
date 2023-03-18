package co.edu.uniquindio.uniMarket.DTO;

import co.edu.uniquindio.uniMarket.entidades.MetodoPago;

import java.util.List;

public class CompraDTO {

    private MetodoPago metodoPago;

    private Integer codigoUsuario;

    private List<DetalleCompraDTO> detalleCompraDTO;

}
