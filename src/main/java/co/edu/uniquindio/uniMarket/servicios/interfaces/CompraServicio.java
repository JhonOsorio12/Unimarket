package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.CompraDTO;
import co.edu.uniquindio.uniMarket.DTO.CompraGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Compra;
import co.edu.uniquindio.uniMarket.entidades.DetalleCompra;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface CompraServicio {

    int crearCompra(CompraDTO compraDTO) throws Exception;

    List<CompraGetDTO> listarComprasUsuario(Integer codigoUsuario) throws Exception;

    CompraGetDTO obtenerCompra(int codigoCompra) throws Exception;

    Compra obtener(Integer codigoCompra) throws Exception;





}
