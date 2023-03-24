package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.ComentarioDTO;
import co.edu.uniquindio.uniMarket.DTO.ComentarioGetDTO;

import java.util.List;

public interface ComentarioServicio {

    ComentarioGetDTO crearComentario(ComentarioDTO comentarioDTO);

    List<ComentarioGetDTO> listarComentarios(Integer codigoProducto);

}
