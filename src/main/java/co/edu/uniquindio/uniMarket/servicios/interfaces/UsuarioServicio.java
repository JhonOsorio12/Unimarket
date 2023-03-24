package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.CuentaPremiumDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioGetDTO;

public interface UsuarioServicio {

    int registrarUsuario(UsuarioDTO usuarioDTO) throws Exception;

    int actualizarUsuario(Integer codigoUsuario, UsuarioDTO usuarioDTO) throws Exception;

    int eliminarUsuario(Integer codigoUsuario) throws Exception;

    UsuarioGetDTO obtenerUsuario(Integer codigoUsuario) throws Exception;

    int crearCuentaPremium(CuentaPremiumDTO cuentaPremiumDTO);

    //actualizar, eliminar


}
