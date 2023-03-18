package co.edu.uniquindio.uniMarket.DTO;

import co.edu.uniquindio.uniMarket.entidades.MetodoPago;

import java.time.LocalDate;
import java.util.List;

public class CompraGetDTO {

    private Integer codigoCompra;

    private LocalDate fechaCreacion;

    private float valorTotal;

    private MetodoPago metodoPago;

    private Integer codigoUsuario;

    private List<DetalleCompraDTO> detalleCompraDTO;


}
