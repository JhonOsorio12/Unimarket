package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.ProductoModeradorDTO;
import co.edu.uniquindio.uniMarket.entidades.*;

import java.util.List;

public interface ModeradorServicio {

    void validarProducto(ProductoModeradorDTO producto) throws Exception;

    void rechazarProducto(ProductoModeradorDTO producto) throws Exception;

    Moderador obtener(Integer codigoModerador) throws Exception;

}
