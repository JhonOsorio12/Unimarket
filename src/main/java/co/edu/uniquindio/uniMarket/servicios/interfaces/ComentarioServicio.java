package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.ComentarioDTO;
import co.edu.uniquindio.uniMarket.DTO.ComentarioGetDTO;

import java.util.List;

public interface ComentarioServicio {

    int crearComentario(ComentarioDTO comentarioDTO) throws Exception;

    List<ComentarioGetDTO> listarComentarios(Integer codigoProducto) throws Exception;

}
