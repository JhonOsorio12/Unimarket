package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.CuentaPremiumDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Usuario;

public interface UsuarioServicio {

    int registrarUsuario(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioGetDTO actualizarUsuario(int codigoUsuario, UsuarioDTO usuario) throws Exception;

    int eliminarUsuario(Integer codigoUsuario) throws Exception;

    UsuarioGetDTO obtenerUsuario(Integer codigoUsuario) throws Exception;

    Usuario obtener(Integer codigoUsuario) throws Exception;

    int marcarFavorito(Integer codigoUsuario, Integer codigoProducto);

    int eliminarFavorito(Integer codigoUsuario, Integer codigoProducto);

    int crearCuentaPremium(CuentaPremiumDTO cuentaPremiumDTO);

    //actualizar, eliminar


}
