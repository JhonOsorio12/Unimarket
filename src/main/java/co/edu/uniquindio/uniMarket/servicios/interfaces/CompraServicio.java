package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.CompraDTO;
import co.edu.uniquindio.uniMarket.DTO.CompraGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Compra;

import java.util.List;

public interface CompraServicio {

    int crearCompra(CompraDTO compraDTO) throws  Exception;

    List<CompraGetDTO> listarComprasUsuario(Integer codigoUsuario);

    Compra obtener(Integer codigoCompra) throws Exception;



}
