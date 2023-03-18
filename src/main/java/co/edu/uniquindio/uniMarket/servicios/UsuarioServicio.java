package co.edu.uniquindio.uniMarket.servicios;

import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioGetDTO;

public interface UsuarioServicio {

    int registrarUsuario(UsuarioDTO usuarioDTO);

    int actualizarUsuario(Integer codigoUsuario, UsuarioDTO usuarioDTO);

    UsuarioGetDTO obtenerUsuario(Integer codigoUsuario);




}
