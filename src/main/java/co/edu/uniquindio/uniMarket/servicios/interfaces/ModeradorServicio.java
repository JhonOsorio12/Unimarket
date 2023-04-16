package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.ProductoModeradorDTO;
import co.edu.uniquindio.uniMarket.entidades.Estado;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.entidades.ProductoModerador;

import java.util.List;

public interface ModeradorServicio {

    void validarProducto(ProductoModeradorDTO producto) throws Exception;

    void rechazarProducto(ProductoModeradorDTO producto) throws Exception;

}
